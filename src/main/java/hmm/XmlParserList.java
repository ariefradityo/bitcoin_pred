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


/**
XmlParserList Interface.
This interface defines XML parser names, which are used by hmm classes.
Currently, only sun and ibm parsers are included
in order to facilitate easy distribution.

@author  Hyoungsoo Yoon
@version  0.3
*/
public interface XmlParserList {
    final static String IBM_PARSER = "ibm";
    final static String SUN_PARSER = "sun";
    // Only two parsers are supported at the moment.
}
