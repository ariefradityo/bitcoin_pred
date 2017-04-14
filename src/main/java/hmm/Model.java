//////////////////////////////////////////////////////////////////////////
// The contents of this file are subject to the Mozilla Public License
// Version 1.0 (the "License"); you may not use this file except in
// compliance with the License. You may obtain a copy of the License at
// http://www.mozilla.org/MPL/
//
// Software distributed under the License is distributed on an "AS IS"
// basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
// License for the specific language governing rights and limitations under
// the License.
//
// The Original Code is Hidden Markov Model Library in Java.
//
// The Initial Developer of the Original Code is Hyoungsoo Yoon.
// Portions created by Hyoungsoo Yoon are
// Copyright (C) 1999 Hyoungsoo Yoon.  All Rights Reserved.
//
// Contributor(s):
//
//////////////////////////////////////////////////////////////////////////


/**
Hidden Markov Model Library in Java.
Please refer to Rabiner 1989.
All algorithms are directly taken from this article.
Notations and variable names also closely follow the conventions used in this paper.

@copyright  Hyoungsoo Yoon
@date  Feb 21st, 1999
*/
package hmm;

import org.w3c.dom.*;
import java.util.*;
import java.io.*;

/**
Markov model with symbol-generation probabilities.
The class Hmm is built on top of this class.
Please refer to Rabiner 1989.

@author  Hyoungsoo Yoon
@version  0.3
*/
public class Model implements Serializable, XmlParserList {

    //<Static_Fields>
    private static final int MAX_NUM_STATES = 1000;
    private static final int MAX_NUM_SYMBOLS = 1000;
    private static double MIN_PROB = 0.00001;
    private static Random rand = new Random();
    //</Static_Fields>
    
    //<Static_Methods>
    public static int getMaxNumStates() {
        return MAX_NUM_STATES;
    }
    public static int getMaxNumSymbols() {
        return MAX_NUM_SYMBOLS;
    }
    public static double getMinProb() {
        return MIN_PROB;
    }
    public static double setMinProb(double p) {
        if(p >= 0.0 && p < 1.0) {
            MIN_PROB = p;
        }
        return MIN_PROB;
    }
    //</Static_Methods>


    //<Private_Fields>
    /**
    @serial
    */
    private int  num_states = 0;
    /**
    @serial
    */
    private int  num_symbols = 0;
    /**
    @serial
    */
    private int  ll_bound = 0;
    /**
    @serial
    */
    private int  ur_bound = 0;
    
    /**
    @serial  Transition probability (num_states x num_states)
    */
    private double[][] a;  
    /**
    @serial  Symbol generating probability (num_states x num_symbols)
    */
    private double[][] b; 
    /**
    @serial  Initial state probability (1 x num_states)
    */
    private double[] pi;
    private transient int[] l_limit;      // a[][] has a band of non-zero elements
    private transient int[] r_limit;     // For each row, columns below l_limit and above r_limit are zero
    //</Private_Fields>


    //<Protectd_Fields>
    //</Protectd_Fields>
    
    
    //<Public_Fields>
    //</Public_Fields>


    //<Constructors>
    /**
    Construct Model with a minimum size (number of states: 1, number of symbols: 1).
    The elements of the matrices are initialized by random values.
    */
    public Model() {
        this(true);
    }
    /**
    Construct Model with a minimum size (number of states: 1, number of symbols: 1).
    @param bRandomize If true, the elements of the matrices are initialized by random values
    */
    public Model(boolean bRandomize) {
        this(1,1,bRandomize);
    }
    /**
    Construct Model by specifying dimensions.
    The elements of the matrices are initialized by random values.
    @param nstates Number of states of a Markov model
    @param nsymbols Number of symbols generated by the model
    */
    public Model(int nstates, int nsymbols) {
        this(nstates, nsymbols, true);
    }
    /**
    Construct Model by specifying dimensions.
    @param nstates Number of states of a Markov model
    @param nsymbols Number of symbols generated by the model
    @param bRandomize If true, the elements of the matrices are initialized by random values
    */
    public Model(int nstates, int nsymbols, boolean bRandomize) {
        this(nstates, nsymbols, nstates-1, nstates-1, bRandomize);
    }
    /**
    Construct Model by specifying dimensions.
    The elements of the matrices are initialized by random values.
    @param nstates Number of states of a Markov model
    @param nsymbols Number of symbols generated by the model
    @param llb Lower-left bound of the non-zero band of the transition matrix (diagonal: 0)
    @param urb Upper-right bound of the non-zero band of the transition matrix (diagonal: 0)
    */
    public Model(int nstates, int nsymbols, int llb, int urb) {
        this(nstates, nsymbols, llb, urb, true);
    }    
    /**
    Construct Model by specifying dimensions.
    @param nstates Number of states of a Markov model
    @param nsymbols Number of symbols generated by the model
    @param llb Lower-left bound of the non-zero band of the transition matrix (diagonal: 0)
    @param urb Upper-right bound of the non-zero band of the transition matrix (diagonal: 0)
    @param bRandomize If true, the elements of the matrices are initialized by random values
    */
    public Model(int nstates, int nsymbols, int llb, int urb, boolean bRandomize) {
        num_states = nstates;
        num_symbols = nsymbols;
        
        a = new double[num_states][num_states];
        b = new double[num_states][num_symbols];
        pi = new double[num_states];
        l_limit = new int[num_states];
        r_limit = new int[num_states];
        
        ll_bound = (llb < num_states && llb >= 0) ? llb : (num_states-1);
        ur_bound = (urb < num_states && urb >= 0) ? urb : (num_states-1);
        setLimits();
        setProbability(bRandomize);
    }
    /**
    Construct Model by loading model parameters from an XML file.
    @param file Name of input file
    */
    public Model(String file) {
        this(file, true);
    }
    /**
    Construct Model by loading model parameters from a file.
    @param file Name of input file
    @param bXml If true, read input file in XML format
    */
    public Model(String file, boolean bXml) {
//        if(bXml) {
//            loadModelXml(file);
//        } else {
//            loadModelAscii(file);
//        }
    }
    //</Constructors>


    //<Private_Methods>
    private void setLimits() {
        for(int i=0;i<num_states;i++) {
            l_limit[i] = (i-ll_bound >=0) ? (i-ll_bound) : 0;
            r_limit[i] = (i+ur_bound <= num_states-1) ? (i+ur_bound) : (num_states-1);
        }
    }
    private void setLeftRight(int llb, int urb) {
        ll_bound = (llb < num_states && llb >= 0) ? llb : (num_states-1);
        ur_bound = (urb < num_states && urb >= 0) ? urb : (num_states-1);
        setLimits();
        resetA();
    }    

    private void clearA() {
        for(int i=0;i<num_states;i++) {
            for(int j=0;j<num_states;j++) {
                a[i][j] = 0.0;
            }
        }
    }    
    private void clearB() {
        for(int i=0;i<num_states;i++) {
            for(int k=0;k<num_symbols;k++) {
                b[i][k] = 0.0;
            }
        }
    }    
    private void clearPi() {
        for(int j=0;j<num_states;j++) {
            pi[j] = 0.0;
        }
    }    

    private void resetA() {
        resetA(true);
    }
    private void resetA(boolean bRandomize) {
        clearA();
        setA(bRandomize);
    }
    private void resetB() {
        resetB(true);
    }
    private void resetB(boolean bRandomize) {
        clearB();
        setB(bRandomize);
    }
    private void resetPi() {
        resetPi(true);
    }
    private void resetPi(boolean bRandomize) {
        clearPi();
        setPi(bRandomize);
    }

    private void normalizeRowA(int i) throws ProbabilityUnnormalizableException {        
        double sum = 0.0;
        for(int j=l_limit[i];j<=r_limit[i];j++) {
            sum += a[i][j];
        }
        sum -= (r_limit[i] - l_limit[i] + 1) * MIN_PROB;
        if(sum <= 0.0) {
            throw new ProbabilityUnnormalizableException("A: Row " + i + " unnormalizable!");
        }    
        double factor = (1.0 - (r_limit[i] - l_limit[i] + 1) * MIN_PROB)/sum;
        for(int j=l_limit[i];j<=r_limit[i];j++) {
            a[i][j] *= factor;
            a[i][j] += (1.0 - factor) * MIN_PROB;
        }
    }
    private void normalizeRowA() throws ProbabilityUnnormalizableException {
        for(int i=0;i<num_states;i++) {
            normalizeRowA(i);
        }
    }    
    private void normalizeRowB(int i) throws ProbabilityUnnormalizableException {        
        double sum = 0.0;
        for(int k=0;k<num_symbols;k++) {
            sum += b[i][k];
        }
        sum -= num_symbols * MIN_PROB;
        if(sum <= 0.0) {
            throw new ProbabilityUnnormalizableException("B: Row " + i + " unnormalizable!");
        }    
        double factor = (1.0 - num_symbols * MIN_PROB)/sum;
        for(int k=0;k<num_symbols;k++) {
            b[i][k] *= factor;
            b[i][k] += (1.0 - factor) * MIN_PROB;
        }
    }
    private void normalizeRowB() throws ProbabilityUnnormalizableException {
        for(int i=0;i<num_states;i++) {
            normalizeRowB(i);
        }
    }    
    private void normalizeRowPi() throws ProbabilityUnnormalizableException {        
        double sum = 0.0;
        for(int j=0;j<num_states;j++) {
            sum += pi[j];
        }
        sum -= num_states * MIN_PROB;
        if(sum <= 0.0) {
            throw new ProbabilityUnnormalizableException("Pi: Unnormalizable!");
        }    
        double factor = (1.0 - num_states * MIN_PROB)/sum;
        for(int j=0;j<num_states;j++) {
            pi[j] *= factor;
            pi[j] += (1.0 - factor) * MIN_PROB;
        }
    }
    //</Private_Methods>

    
    //<Protectd_Methods>
    //</Protectd_Methods>


    //<Public_Methods>
    /**
    @return  Number of states in the Markov model 
    */
    public int getNumStates() {
        return num_states;
    }
    /**
    @return  Number of observable symbols 
    */
    public int getNumSymbols() {
        return num_symbols;
    }
    
    /**
    @param i Row number of the state-transition matrix A
    @return  Left limit (column number) outside of which the transition probability is zero 
    */
    public int getLLimit(int i) {
        return l_limit[i];
    }    
    /**
    @param i Row number of the state-transition matrix A
    @return  Right limit (column number) outside of which the transition probability is zero 
    */
    public int getRLimit(int i) {
        return r_limit[i];
    }
    
    /**
    Initialize the state-transition matrix A with random values.
    */
    public void setA() {
        setA(true);
    }
    /**
    Initialize the state-transition matrix A.
    @param bRandomize If true, initialize A with random values
    */
    public void setA(boolean bRandomize) {
        if(bRandomize == true) {
            for(int i=0;i<num_states;i++) {
                for(int j=l_limit[i];j<=r_limit[i];j++) {
                    a[i][j] = rand.nextDouble() + MIN_PROB;
                }
            }
            try {
                normalizeRowA();
            } catch(ProbabilityUnnormalizableException exc) {
                exc.printStackTrace();
            }
        }
        else {
            for(int i=0;i<num_states;i++) {
                for(int j=l_limit[i];j<=r_limit[i];j++) {
                    a[i][j] = MIN_PROB;
                }
                a[i][i] = 1.0 - (r_limit[i] - l_limit[i])*MIN_PROB;
            }
        }
    }
    /**
    @param i Row number of the state-transition matrix A
    @param j Column number of state-transition matrix A
    @param p Probability value    
    */
    public void setA(int i, int j, double p) {
        a[i][j] = (p>MIN_PROB) ? p : MIN_PROB;
    }
    /**
    @param i Row number of the state-transition matrix A
    @param j Column number of state-transition matrix A
    @param p Probability value
    @param bCheck If true, check the index bounds and the range of p
    @return true, if successful
    */
    public boolean setA(int i, int j, double p, boolean bCheck) {
        if((bCheck == false) || 
            (i>=0 && i<num_states && j>=l_limit[i] && j<=r_limit[i] && p<=1.0 && p>=0.0)) {
            setA(i,j,p);
            return true;
        } else {
            return false;
        }
    }
    /**
    @param i Row number of the state-transition matrix A
    @param p Row vector of probability values
    */
    public void setA(int i, double[] p) {
        for(int j=l_limit[i];j<=r_limit[i];j++) {
            a[i][j] = (p[j]>MIN_PROB) ? p[j] : MIN_PROB;
        }
    }
    /**
    @param i Row number of the state-transition matrix A
    @param p Row vector of probability values
    @param bNormalize If true, renormalize the row in case p is not normalized
    @return false, if A is unnormalized
    */
    public boolean setA(int i, double[] p, boolean bNormalize) {
        setA(i,p);
        if(bNormalize == false) {
            return true;
        } else {
            try {
                normalizeRowA(i);
            } catch(ProbabilityUnnormalizableException exc) {
                exc.printStackTrace();
                return false;
            }
            return true;
        }
    }
    /**
    @param p Matrix of probability values
    */
    public void setA(double[][] p) {
        for(int i=0;i<num_states;i++) {
            for(int j=l_limit[i];j<=r_limit[i];j++) {
                a[i][j] = (p[i][j]>MIN_PROB) ? p[i][j] : MIN_PROB;
            }
        }
    }
    /**
    @param p Matrix of probability values
    @param bNormalize If true, renormalize each row in case p is not normalized
    @return false, if A is unnormalized
   */
    public boolean setA(double[][] p, boolean bNormalize) {
        setA(p);
        if(bNormalize == false) {
            return true;
        } else {
            try {
                normalizeRowA();
            } catch(ProbabilityUnnormalizableException exc) {
                exc.printStackTrace();
                return false;
            }
            return true;
        }
    }
    
    /**
    Initialize the symbol-generation matrix B with random values.
    */
    public void setB() {
        setB(true);
    }
    /**
    Initialize the symbol-generation matrix B.
    @param bRandomize If true, initialize B with random values
    */
    public void setB(boolean bRandomize) {
        if(bRandomize == true) {
            for(int i=0;i<num_states;i++) {
                for(int k=0;k<num_symbols;k++) {
                    b[i][k] = rand.nextDouble() + MIN_PROB;
                }
            }    
            try {
                normalizeRowB();
            } catch(ProbabilityUnnormalizableException exc) {
                exc.printStackTrace();
            }
        }
        else {
            for(int i=0;i<num_states;i++) {
                for(int k=0;k<num_symbols;k++) {
                    b[i][k] = 1.0/num_symbols;
                }
            }
        }
    }
    /**
    @param i Row number of the symbol-generation matrix B
    @param k Column number of symbol-generation matrix B
    @param p Probability value
    */
    public void setB(int i, int k, double p) {
        b[i][k] = (p>MIN_PROB) ? p : MIN_PROB;
    }
    /**
    @param i Row number of the symbol-generation matrix B
    @param k Column number of symbol-generation matrix B
    @param p Probability value
    @param bCheck If true, check the index bounds and the range of p
    @return true, if successful
    */
    public boolean setB(int i, int k, double p, boolean bCheck) {
        if((bCheck == false) || 
            (i>=0 && i<num_states && k>=0 && k<num_symbols && p<=1.0 && p>=0.0)) {
            setB(i,k,p);
            return true;
        } else {
            return false;
        }
    }
    /**
    @param i Row number of the symbol-generation matrix B
    @param p Row vector of probability values
    */
    public void setB(int i, double[] p) {
        for(int k=0;k<num_symbols;k++) {
            b[i][k] = (p[k]>MIN_PROB) ? p[k] : MIN_PROB;
        }
    }
    /**
    @param i Row number of the symbol-generation matrix B
    @param p Row vector of probability values
    @param bNormalize If true, renormalize the row in case p is not normalized
    @return false, if B is unnormalized
    */
    public boolean setB(int i, double[] p, boolean bNormalize) {
        setB(i,p);
        if(bNormalize == false) {
            return true;
        } else {
            try {
                normalizeRowB(i);
            } catch(ProbabilityUnnormalizableException exc) {
                exc.printStackTrace();
                return false;
            }
            return true;
        }
    }
    /**
    @param p Matrix of probability values
    */
    public void setB(double[][] p) {
        for(int i=0;i<num_states;i++) {
            for(int k=0;k<num_symbols;k++) {
                b[i][k] = (p[i][k]>MIN_PROB) ? p[i][k] : MIN_PROB;
            }
        }
    }
    /**
    @param p Matrix of probability values
    @param bNormalize If true, renormalize each row in case p is not normalized
    @return false, if B is unnormalized
    */
    public boolean setB(double[][] p, boolean bNormalize) {
        setB(p);
        if(bNormalize == false) {
            return true;
        } else {
            try {
                normalizeRowB();
            } catch(ProbabilityUnnormalizableException exc) {
                exc.printStackTrace();
                return false;
            }
            return true;
        }
    }
    
    /**
    Initialize the initial-state row vector Pi with random values.
    */
    public void setPi() {
        setPi(true);
    }
    /**
    Initialize the initial-state row vector Pi.
    @param bRandomize If true, initialize Pi with random values
    */
    public void setPi(boolean bRandomize) {
        if(bRandomize == true) {
            for(int j=0;j<num_states;j++) {
                pi[j] = rand.nextDouble() + num_states*MIN_PROB;
            }    
            try {
                normalizeRowPi();
            } catch(ProbabilityUnnormalizableException exc) {
                exc.printStackTrace();
            }
        }
        else {
            for(int j=0;j<num_states;j++) {
                pi[j] = 1.0/num_states;
            }    
        }
    }
    /**
    @param j Column number of initial-state row vector Pi
    @param p Probability value
    */
    public void setPi(int j, double p) {
        pi[j] = (p>MIN_PROB) ? p : MIN_PROB;
    }
    /**
    @param j Column number of initial-state row vector Pi
    @param p Probability value
    @param bCheck If true, check the index bounds and the range of p
    @return true, if successful
    */
    public boolean setPi(int j, double p, boolean bCheck) {
        if((bCheck == false) || 
            (j>=0 && j<num_states && p<=1.0 && p>=0.0)) {
            setPi(j,p);
            return true;
        } else {
            return false;
        }
    }    
    public void setPi(double[] p) {
        for(int j=0;j<num_states;j++) {
            pi[j] = (p[j]>MIN_PROB) ? p[j] : MIN_PROB;
        }
    }    
    /**
    @param p Row vector of probability values
    @param bNormalize If true, renormalize Pi in case p is not normalized
    @return false, if Pi is unnormalized
    */
    public boolean setPi(double[] p, boolean bNormalize) {
        setPi(p);
        if(bNormalize == false) {
            return true;
        } else {
            try {
                normalizeRowB();
            } catch(ProbabilityUnnormalizableException exc) {
                exc.printStackTrace();
                return false;
            }
            return true;
        }
    }
    
    /**
    @param i Row number of the state-transition matrix A
    @param j Column number of state-transition matrix A
    @return Probability value of A[i][j]
    */
    public double getA(int i, int j) {
        return a[i][j];
    }
    /**
    @param i Row number of the state-transition matrix A
    @return Row vector of probability values of A[i][]
    */
    public double[] getA(int i) {
        return a[i];
    }
    /**
    @return State-transition matrix A
    */
    public double[][] getA() {
        return a;
    }
    
    /**
    @param i Row number of the symbol-generation matrix B
    @param k Column number of symbol-generation matrix B
    @return Probability value of B[i][k]
    */
    public double getB(int i, int k) {
        return b[i][k];
    }
    /**
    @param i Row number of the symbol-generation matrix B
    @return Row vector of probability value of B[i][]
    */
    public double[] getB(int i) {
        return b[i];
    }
    /**
    @return Symbol-generation matrix B
    */
    public double[][] getB() {
        return b;
    }
    
    /**
    @param j Column number of inital-state row vector Pi
    @return Probability value of Pi[j]
    */
    public double getPi(int j) {
        return pi[j];
    }
    /**
    @return Inital-state row vector Pi
    */
    public double[] getPi() {
        return pi;
    }
    /**
    @param t Index of observation sequence
    @param j Column number of state vector Pi[t][] at time t
    @return Probability value of Pi[t][j]
    */
    public double getPi(int t, int j) {
        if(t==0) {
            return pi[j];
        } else {
            double sum = 0.0;
            for(int i=0;i<num_states;i++) {
                sum += getPi(t-1,i) * a[i][j];
            }
            return sum;
        }    
    }

    /**
    Intialize probability matrices, A, B, and Pi, with random values.
    */
    public void setProbability() {
        setProbability(true);
    }
    /**
    Intialize probability matrices, A, B, and Pi.
    @param bRandomize If true, initialize probability matrices with random values
    */
    public void setProbability(boolean bRandomize) {
        setA(bRandomize);
        setB(bRandomize);
        setPi(bRandomize);
    }
    
//    /**
//    Load model parameters from an XML file.
//    @param file Name of input file
//    */
//    public void loadModel(String file) {
//        loadModel(file, true);
//    }
//    /**
//    Load model parameters from a file.
//    @param file Name of input file
//    @param bXml If true, read file in XML format
//    */
//    public void loadModel(String file, boolean bXml) {
//        if(bXml) {
//            loadModelXml(file);
//        } else {
//            loadModelAscii(file);
//        }
//    }
//    /**
//    Load model parameters from an Ascii file.
//    @param file Name of input file
//    */
//    public void loadModelAscii(String file) {
//        try {
//            BufferedReader in =
//                new BufferedReader(
//                    new FileReader(file));
//
//            num_states = Integer.valueOf(in.readLine()).intValue();
//            num_symbols = Integer.valueOf(in.readLine()).intValue();
//            ll_bound = Integer.valueOf(in.readLine()).intValue();
//            ur_bound = Integer.valueOf(in.readLine()).intValue();
//            // check validaty of these parameters
//
//            a = new double[num_states][num_states];
//            b = new double[num_states][num_symbols];
//            pi = new double[num_states];
//            l_limit = new int[num_states];
//            r_limit = new int[num_states];
//            setLimits();
//
//            StringTokenizer stkn = null;
//            for(int i=0;i<num_states;i++) {
//                stkn = new StringTokenizer(in.readLine());
//                int j = 0;
//                while(stkn.hasMoreTokens()) {
//                    a[i][j++] = Double.valueOf(stkn.nextToken()).doubleValue();
//                }
//            }
//            // check if j==num_states
//            for(int i=0;i<num_states;i++) {
//                stkn = new StringTokenizer(in.readLine());
//                int k = 0;
//                while(stkn.hasMoreTokens()) {
//                    b[i][k++] = Double.valueOf(stkn.nextToken()).doubleValue();
//                }
//            }
//            // check if k==num_symbols
//            stkn = new StringTokenizer(in.readLine());
//            int j = 0;
//            while(stkn.hasMoreTokens()) {
//                pi[j++] = Double.valueOf(stkn.nextToken()).doubleValue();
//            }
//            // check if j==num_states
//
//             in.close();
//        } catch(FileNotFoundException exc) {
//            System.out.println("File Not Found: " + file);
//        } catch(IOException exc) {
//            exc.printStackTrace();
//        }
//    }
//    /**
//    Load model parameters from an XML file.
//    @param file Name of input file
//    */
//    public void loadModelXml(String file) {
//
//        // parse xml file!!!!
//        DomParserFactory.setParser(SUN_PARSER);
//        Document doc = DomParserFactory.openLocalDocument(file);
//
//        NodeList nodeList = null;
//        NodeList childList = null;
//        Node childNode = null;
//        Element nodeElement = null;
//        Element childElement = null;
//        String strElement = null;
//
//        nodeList = doc.getElementsByTagName( "states" );
//        nodeElement = (Element) nodeList.item(0);
//        strElement = nodeElement.getAttribute("count");
//        num_states = Integer.valueOf(strElement).intValue();
//
//        nodeList = doc.getElementsByTagName( "symbols" );
//        nodeElement = (Element) nodeList.item(0);
//        strElement = nodeElement.getAttribute("count");
//        num_symbols = Integer.valueOf(strElement).intValue();
//
//        nodeList = doc.getElementsByTagName( "bounds" );
//        nodeElement = (Element) nodeList.item(0);
//        strElement = nodeElement.getAttribute("ll_bound");
//        ll_bound = Integer.valueOf(strElement).intValue();
//        nodeElement = (Element) nodeList.item(0);
//        strElement = nodeElement.getAttribute("ur_bound");
//        ur_bound = Integer.valueOf(strElement).intValue();
//        // check validaty of these parameters
//
//        a = new double[num_states][num_states];
//        b = new double[num_states][num_symbols];
//        pi = new double[num_states];
//        l_limit = new int[num_states];
//        r_limit = new int[num_states];
//        setLimits();
//
//        nodeList = doc.getElementsByTagName( "transition_matrix" );
//        childList = nodeList.item(0).getChildNodes(); // "element"
//        for (int m = 0 ; m < childList.getLength() ; m++ ){
//	          childNode = childList.item(m);
//	          if(childNode.getNodeType() == Node.ELEMENT_NODE ){
//		            childElement = (Element) childNode;
//	          } else {
//		            continue;
//	          }
//            String row = childElement.getAttribute("row");
//            String col = childElement.getAttribute("col");
//            String value = childElement.getAttribute("value");
//            if( row != null && col != null ){
//                int i = Integer.valueOf(row).intValue();
//                int j = Integer.valueOf(col).intValue();
//                a[i][j] = Double.valueOf(value).doubleValue();
//            }
//        }
//
//        nodeList = doc.getElementsByTagName( "symbol_generation_matrix" );
//        childList = nodeList.item(0).getChildNodes(); // "element"
//        for (int m = 0 ; m < childList.getLength() ; m++ ){
//	         childNode = childList.item(m);
//	         if(childNode.getNodeType() == Node.ELEMENT_NODE ){
//		           childElement = (Element) childNode;
//	         } else {
//		           continue;
//	         }
//            String row = childElement.getAttribute("row");
//            String col = childElement.getAttribute("col");
//            String value = childElement.getAttribute("value");
//            if( row != null && col != null ){
//                int i = Integer.valueOf(row).intValue();
//                int k = Integer.valueOf(col).intValue();
//                b[i][k] = Double.valueOf(value).doubleValue();
//            }
//        }
//
//        nodeList = doc.getElementsByTagName( "initial_state_vector" );
//        childList = nodeList.item(0).getChildNodes(); // "element"
//        for (int m = 0 ; m < childList.getLength() ; m++ ){
//	          childNode = childList.item(m);
//	          if(childNode.getNodeType() == Node.ELEMENT_NODE ){
//		            childElement = (Element) childNode;
//	          } else {
//		            continue;
//	          }
//            String row = childElement.getAttribute("row");
//            String value = childElement.getAttribute("value");
//            if( row != null){
//                int j = Integer.valueOf(row).intValue();
//                pi[j] = Double.valueOf(value).doubleValue();
//            }
//        }
//
//    }
//    /**
//    Save model parameters to a given file in XML format.
//    @param file Name of output file
//    */
//    public void saveModel(String file) {
//        saveModel(file, true);
//    }
//    /**
//    Save model parameters to a given file.
//    @param file Name of output file
//    @param bXml If true, save model in XML format
//    */
//    public void saveModel(String file, boolean bXml) {
//        if(bXml) {
//            saveModelXml(file);
//        } else {
//            saveModelAscii(file);
//        }
//    }
//    /**
//    Save model parameters to a given file in Ascii format.
//    @param file Name of output file
//    */
//    public void saveModelAscii(String file) {
//        try {
//            PrintWriter out =
//                new PrintWriter(
//                    new BufferedWriter(
//                        new FileWriter(file)));
//
//            out.println(num_states);
//            out.println(num_symbols);
//            out.println(ll_bound);
//            out.println(ur_bound);
//
//            for(int i=0;i<num_states;i++) {
//                for(int j=0;j<num_states;j++) {
//                    out.print(a[i][j]);
//                    out.print("\t");
//                }
//                out.println();
//            }
//            for(int i=0;i<num_states;i++) {
//                for(int k=0;k<num_symbols;k++) {
//                    out.print(b[i][k]);
//                    out.print("\t");
//                }
//                out.println();
//            }
//            for(int j=0;j<num_states;j++) {
//                out.print(pi[j]);
//                out.print("\t");
//            }
//            out.println();
//
//            //
//            //out.flush();
//            out.close();
//        } catch(FileNotFoundException exc) {
//            System.out.println("File Not Found: " + file);
//        } catch(IOException exc) {
//            exc.printStackTrace();
//        }
//    }
//    /**
//    Save model parameters to a given file in XML format.
//    @param file Name of output file
//    */
//    public void saveModelXml(String file) {
//        try {
//            PrintWriter out =
//                new PrintWriter(
//                    new BufferedWriter(
//                        new FileWriter(file)));
//
//            out.println("<?xml version=\"1.0\"?>");
//            out.println("<!DOCTYPE hmm SYSTEM \"hmm03.dtd\">");
//            out.println(" ");
//            out.println("<hmm version=\"0.3\">");
//            out.println("<name>");
//            out.println("Hidden Markov Model Test");
//            out.println("</name>");
//            out.println("<model>");
//            out.print("<states count=\"");
//            out.print(num_states);
//            out.println("\">");
//            out.println("</states>");
//            out.print("<symbols count=\"");
//            out.print(num_symbols);
//            out.println("\">");
//            out.println("</symbols>");
//            out.print("<bounds ll_bound=\"");
//            out.print(ll_bound);
//            out.print("\" ur_bound=\"");
//            out.print(ur_bound);
//            out.println("\">");
//            out.println("</bounds>");
//
//            out.println("<transition_matrix>");
//            for(int i=0;i<num_states;i++) {
//                for(int j=0;j<num_states;j++) {
//                    out.print("<element row=\"" + i + "\" " + "col=\"" + j + "\" "
//                              + "value=\"" + a[i][j] + "\"/>");
//                }
//                out.println();
//            }
//            out.println("</transition_matrix>");
//            out.println("<symbol_generation_matrix>");
//            for(int i=0;i<num_states;i++) {
//                for(int k=0;k<num_symbols;k++) {
//                    out.print("<element row=\"" + i + "\" " + "col=\"" + k + "\" "
//                              + "value=\"" + b[i][k] + "\"/>");
//                }
//                out.println();
//            }
//            out.println("</symbol_generation_matrix>");
//            out.println("<initial_state_vector>");
//            for(int j=0;j<num_states;j++) {
//                out.print("<element row=\"" + j + "\" "
//                          + "value=\"" + pi[j] + "\"/>");
//            }
//            out.println();
//            out.println("</initial_state_vector>");
//            out.println("</model>");
//            out.println("</hmm>");
//
//            //
//            //out.flush();
//            out.close();
//        } catch(FileNotFoundException exc) {
//            System.out.println("File Not Found: " + file);
//        } catch(IOException exc) {
//            exc.printStackTrace();
//        }
//    }
//    //</Public_Methods>
//
//
//    //<Main_Method>
//    /**
//    This function is provided for testing purposes.
//    Direct modification of this class is not recommended
//    unless there is a bug. (In which case please notify me.)
//    Please use inheritance or aggregation (composition).
//    */
//    public static void main(String[] args) {
//        Model node = new Model(4, 5, 0, 1, false);
//        node.setProbability(true);
//
//        for(int i=0;i<node.num_states;i++) {
//            for(int j=0;j<node.num_states;j++) {
//                System.out.print(node.getA(i,j) + "\t");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for(int j=0;j<node.num_states;j++) {
//            System.out.print(node.getPi(j) + "\t");
//        }
//        System.out.println();
//        System.out.println();
//        /*
//        for(int i=0;i<node.num_states;i++) {
//            for(int k=0;k<node.num_symbols;k++) {
//                System.out.print(node.getB(i,k) + "\t");
//            }
//            System.out.println();
//        }
//        */
//
//        try {
//            ObjectOutputStream out =
//                new ObjectOutputStream(
//                    new FileOutputStream("model.hmm"));
//            out.writeObject(node);
//            out.close();
//        } catch(Exception exc) {
//            exc.printStackTrace();
//        }
//
//        Model newNode = null;
//        try {
//            ObjectInputStream in =
//                new ObjectInputStream(
//                    new FileInputStream("model.hmm"));
//            newNode = (Model) in.readObject();
//            in.close();
//        } catch(Exception exc) {
//            exc.printStackTrace();
//        }
//
//        for(int i=0;i<newNode.num_states;i++) {
//            for(int j=0;j<newNode.num_states;j++) {
//                System.out.print(newNode.getA(i,j) + "\t");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for(int j=0;j<newNode.num_states;j++) {
//            System.out.print(newNode.getPi(j) + "\t");
//        }
//        System.out.println();
//        System.out.println();
//        /*
//        for(int i=0;i<newNode.num_states;i++) {
//            for(int k=0;k<newNode.num_symbols;k++) {
//                System.out.print(newNode.getB(i,k) + "\t");
//            }
//            System.out.println();
//        }
//        */
//
//        newNode.saveModel("test.xml", true);
//        newNode.loadModel("test.xml", true);
//
//        //
//        //newNode = new Model("model.xml", true);
//
//        System.out.println(newNode.num_states);
//        System.out.println(newNode.num_symbols);
//        System.out.println(newNode.ll_bound);
//        System.out.println(newNode.ur_bound);
//        for(int i=0;i<newNode.num_states;i++) {
//            for(int jj=0;jj<newNode.num_states;jj++) {
//                System.out.print(newNode.a[i][jj]);
//                System.out.print("\t");
//            }
//            System.out.println();
//        }
//        for(int i=0;i<newNode.num_states;i++) {
//            for(int k=0;k<newNode.num_symbols;k++) {
//                System.out.print(newNode.b[i][k]);
//                System.out.print("\t");
//            }
//            System.out.println();
//        }
//        for(int jjj=0;jjj<newNode.num_states;jjj++) {
//            System.out.print(newNode.pi[jjj]);
//            System.out.print("\t");
//        }
//        System.out.println();
//    }
//    //</Main_Method>
}