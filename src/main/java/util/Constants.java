package util;

/**
 * Created by arief on 21/05/2017.
 */
public class Constants {

    public static final String[][] powerSet = {{"close"}, {"volume"}, {"macd"}, {"rsi"}, {"obv"}, {"atr"}, {"mom"},
            {"usd_close"}, {"gold_close"}, {"close", "volume"}, {"close", "macd"}, {"close", "rsi"}, {"close", "obv"},
            {"close", "atr"}, {"close", "mom"}, {"close", "usd_close"}, {"close", "gold_close"}, {"volume", "macd"},
            {"volume", "rsi"}, {"volume", "obv"}, {"volume", "atr"}, {"volume", "mom"}, {"volume", "usd_close"},
            {"volume", "gold_close"}, {"macd", "rsi"}, {"macd", "obv"}, {"macd", "atr"}, {"macd", "mom"},
            {"macd", "usd_close"}, {"macd", "gold_close"}, {"rsi", "obv"}, {"rsi", "atr"}, {"rsi", "mom"},
            {"rsi", "usd_close"}, {"rsi", "gold_close"}, {"obv", "atr"}, {"obv", "mom"}, {"obv", "usd_close"},
            {"obv", "gold_close"}, {"atr", "mom"}, {"atr", "usd_close"}, {"atr", "gold_close"}, {"mom", "usd_close"},
            {"mom", "gold_close"}, {"usd_close", "gold_close"}, {"close", "volume", "macd"}, {"close", "volume", "rsi"},
            {"close", "volume", "obv"}, {"close", "volume", "atr"}, {"close", "volume", "mom"},
            {"close", "volume", "usd_close"}, {"close", "volume", "gold_close"}, {"close", "macd", "rsi"},
            {"close", "macd", "obv"}, {"close", "macd", "atr"}, {"close", "macd", "mom"},
            {"close", "macd", "usd_close"}, {"close", "macd", "gold_close"}, {"close", "rsi", "obv"},
            {"close", "rsi", "atr"}, {"close", "rsi", "mom"}, {"close", "rsi", "usd_close"},
            {"close", "rsi", "gold_close"}, {"close", "obv", "atr"}, {"close", "obv", "mom"},
            {"close", "obv", "usd_close"}, {"close", "obv", "gold_close"}, {"close", "atr", "mom"},
            {"close", "atr", "usd_close"}, {"close", "atr", "gold_close"}, {"close", "mom", "usd_close"},
            {"close", "mom", "gold_close"}, {"close", "usd_close", "gold_close"}, {"volume", "macd", "rsi"},
            {"volume", "macd", "obv"}, {"volume", "macd", "atr"}, {"volume", "macd", "mom"},
            {"volume", "macd", "usd_close"}, {"volume", "macd", "gold_close"}, {"volume", "rsi", "obv"},
            {"volume", "rsi", "atr"}, {"volume", "rsi", "mom"}, {"volume", "rsi", "usd_close"},
            {"volume", "rsi", "gold_close"}, {"volume", "obv", "atr"}, {"volume", "obv", "mom"},
            {"volume", "obv", "usd_close"}, {"volume", "obv", "gold_close"}, {"volume", "atr", "mom"},
            {"volume", "atr", "usd_close"}, {"volume", "atr", "gold_close"}, {"volume", "mom", "usd_close"},
            {"volume", "mom", "gold_close"}, {"volume", "usd_close", "gold_close"}, {"macd", "rsi", "obv"},
            {"macd", "rsi", "atr"}, {"macd", "rsi", "mom"}, {"macd", "rsi", "usd_close"}, {"macd", "rsi", "gold_close"},
            {"macd", "obv", "atr"}, {"macd", "obv", "mom"}, {"macd", "obv", "usd_close"}, {"macd", "obv", "gold_close"},
            {"macd", "atr", "mom"}, {"macd", "atr", "usd_close"}, {"macd", "atr", "gold_close"},
            {"macd", "mom", "usd_close"}, {"macd", "mom", "gold_close"}, {"macd", "usd_close", "gold_close"},
            {"rsi", "obv", "atr"}, {"rsi", "obv", "mom"}, {"rsi", "obv", "usd_close"}, {"rsi", "obv", "gold_close"},
            {"rsi", "atr", "mom"}, {"rsi", "atr", "usd_close"}, {"rsi", "atr", "gold_close"},
            {"rsi", "mom", "usd_close"}, {"rsi", "mom", "gold_close"}, {"rsi", "usd_close", "gold_close"},
            {"obv", "atr", "mom"}, {"obv", "atr", "usd_close"}, {"obv", "atr", "gold_close"},
            {"obv", "mom", "usd_close"}, {"obv", "mom", "gold_close"}, {"obv", "usd_close", "gold_close"},
            {"atr", "mom", "usd_close"}, {"atr", "mom", "gold_close"}, {"atr", "usd_close", "gold_close"},
            {"mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi"},
            {"close", "volume", "macd", "obv"}, {"close", "volume", "macd", "atr"},
            {"close", "volume", "macd", "mom"}, {"close", "volume", "macd", "usd_close"},
            {"close", "volume", "macd", "gold_close"}, {"close", "volume", "rsi", "obv"},
            {"close", "volume", "rsi", "atr"}, {"close", "volume", "rsi", "mom"},
            {"close", "volume", "rsi", "usd_close"}, {"close", "volume", "rsi", "gold_close"},
            {"close", "volume", "obv", "atr"}, {"close", "volume", "obv", "mom"},
            {"close", "volume", "obv", "usd_close"}, {"close", "volume", "obv", "gold_close"},
            {"close", "volume", "atr", "mom"}, {"close", "volume", "atr", "usd_close"},
            {"close", "volume", "atr", "gold_close"}, {"close", "volume", "mom", "usd_close"},
            {"close", "volume", "mom", "gold_close"}, {"close", "volume", "usd_close", "gold_close"},
            {"close", "macd", "rsi", "obv"}, {"close", "macd", "rsi", "atr"}, {"close", "macd", "rsi", "mom"},
            {"close", "macd", "rsi", "usd_close"}, {"close", "macd", "rsi", "gold_close"},
            {"close", "macd", "obv", "atr"}, {"close", "macd", "obv", "mom"}, {"close", "macd", "obv", "usd_close"},
            {"close", "macd", "obv", "gold_close"}, {"close", "macd", "atr", "mom"},
            {"close", "macd", "atr", "usd_close"}, {"close", "macd", "atr", "gold_close"},
            {"close", "macd", "mom", "usd_close"}, {"close", "macd", "mom", "gold_close"},
            {"close", "macd", "usd_close", "gold_close"}, {"close", "rsi", "obv", "atr"},
            {"close", "rsi", "obv", "mom"}, {"close", "rsi", "obv", "usd_close"},
            {"close", "rsi", "obv", "gold_close"}, {"close", "rsi", "atr", "mom"},
            {"close", "rsi", "atr", "usd_close"}, {"close", "rsi", "atr", "gold_close"},
            {"close", "rsi", "mom", "usd_close"}, {"close", "rsi", "mom", "gold_close"},
            {"close", "rsi", "usd_close", "gold_close"}, {"close", "obv", "atr", "mom"},
            {"close", "obv", "atr", "usd_close"}, {"close", "obv", "atr", "gold_close"},
            {"close", "obv", "mom", "usd_close"}, {"close", "obv", "mom", "gold_close"},
            {"close", "obv", "usd_close", "gold_close"}, {"close", "atr", "mom", "usd_close"},
            {"close", "atr", "mom", "gold_close"}, {"close", "atr", "usd_close", "gold_close"},
            {"close", "mom", "usd_close", "gold_close"}, {"volume", "macd", "rsi", "obv"},
            {"volume", "macd", "rsi", "atr"}, {"volume", "macd", "rsi", "mom"}, {"volume", "macd", "rsi", "usd_close"},
            {"volume", "macd", "rsi", "gold_close"}, {"volume", "macd", "obv", "atr"}, {"volume", "macd", "obv", "mom"},
            {"volume", "macd", "obv", "usd_close"}, {"volume", "macd", "obv", "gold_close"},
            {"volume", "macd", "atr", "mom"}, {"volume", "macd", "atr", "usd_close"},
            {"volume", "macd", "atr", "gold_close"}, {"volume", "macd", "mom", "usd_close"},
            {"volume", "macd", "mom", "gold_close"}, {"volume", "macd", "usd_close", "gold_close"},
            {"volume", "rsi", "obv", "atr"}, {"volume", "rsi", "obv", "mom"}, {"volume", "rsi", "obv", "usd_close"},
            {"volume", "rsi", "obv", "gold_close"}, {"volume", "rsi", "atr", "mom"},
            {"volume", "rsi", "atr", "usd_close"}, {"volume", "rsi", "atr", "gold_close"},
            {"volume", "rsi", "mom", "usd_close"}, {"volume", "rsi", "mom", "gold_close"},
            {"volume", "rsi", "usd_close", "gold_close"}, {"volume", "obv", "atr", "mom"},
            {"volume", "obv", "atr", "usd_close"}, {"volume", "obv", "atr", "gold_close"},
            {"volume", "obv", "mom", "usd_close"}, {"volume", "obv", "mom", "gold_close"},
            {"volume", "obv", "usd_close", "gold_close"}, {"volume", "atr", "mom", "usd_close"},
            {"volume", "atr", "mom", "gold_close"}, {"volume", "atr", "usd_close", "gold_close"},
            {"volume", "mom", "usd_close", "gold_close"}, {"macd", "rsi", "obv", "atr"}, {"macd", "rsi", "obv", "mom"},
            {"macd", "rsi", "obv", "usd_close"}, {"macd", "rsi", "obv", "gold_close"}, {"macd", "rsi", "atr", "mom"},
            {"macd", "rsi", "atr", "usd_close"}, {"macd", "rsi", "atr", "gold_close"},
            {"macd", "rsi", "mom", "usd_close"}, {"macd", "rsi", "mom", "gold_close"},
            {"macd", "rsi", "usd_close", "gold_close"}, {"macd", "obv", "atr", "mom"},
            {"macd", "obv", "atr", "usd_close"}, {"macd", "obv", "atr", "gold_close"},
            {"macd", "obv", "mom", "usd_close"}, {"macd", "obv", "mom", "gold_close"},
            {"macd", "obv", "usd_close", "gold_close"}, {"macd", "atr", "mom", "usd_close"},
            {"macd", "atr", "mom", "gold_close"}, {"macd", "atr", "usd_close", "gold_close"}, {"macd", "mom", "usd_close", "gold_close"}, {"rsi", "obv", "atr", "mom"}, {"rsi", "obv", "atr", "usd_close"}, {"rsi", "obv", "atr", "gold_close"}, {"rsi", "obv", "mom", "usd_close"}, {"rsi", "obv", "mom", "gold_close"}, {"rsi", "obv", "usd_close", "gold_close"}, {"rsi", "atr", "mom", "usd_close"}, {"rsi", "atr", "mom", "gold_close"}, {"rsi", "atr", "usd_close", "gold_close"}, {"rsi", "mom", "usd_close", "gold_close"}, {"obv", "atr", "mom", "usd_close"}, {"obv", "atr", "mom", "gold_close"}, {"obv", "atr", "usd_close", "gold_close"}, {"obv", "mom", "usd_close", "gold_close"}, {"atr", "mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi", "obv"}, {"close", "volume", "macd", "rsi", "atr"}, {"close", "volume", "macd", "rsi", "mom"}, {"close", "volume", "macd", "rsi", "usd_close"}, {"close", "volume", "macd", "rsi", "gold_close"}, {"close", "volume", "macd", "obv", "atr"}, {"close", "volume", "macd", "obv", "mom"}, {"close", "volume", "macd", "obv", "usd_close"}, {"close", "volume", "macd", "obv", "gold_close"}, {"close", "volume", "macd", "atr", "mom"}, {"close", "volume", "macd", "atr", "usd_close"}, {"close", "volume", "macd", "atr", "gold_close"}, {"close", "volume", "macd", "mom", "usd_close"}, {"close", "volume", "macd", "mom", "gold_close"}, {"close", "volume", "macd", "usd_close", "gold_close"}, {"close", "volume", "rsi", "obv", "atr"}, {"close", "volume", "rsi", "obv", "mom"}, {"close", "volume", "rsi", "obv", "usd_close"}, {"close", "volume", "rsi", "obv", "gold_close"}, {"close", "volume", "rsi", "atr", "mom"}, {"close", "volume", "rsi", "atr", "usd_close"}, {"close", "volume", "rsi", "atr", "gold_close"}, {"close", "volume", "rsi", "mom", "usd_close"}, {"close", "volume", "rsi", "mom", "gold_close"}, {"close", "volume", "rsi", "usd_close", "gold_close"}, {"close", "volume", "obv", "atr", "mom"}, {"close", "volume", "obv", "atr", "usd_close"}, {"close", "volume", "obv", "atr", "gold_close"}, {"close", "volume", "obv", "mom", "usd_close"}, {"close", "volume", "obv", "mom", "gold_close"}, {"close", "volume", "obv", "usd_close", "gold_close"}, {"close", "volume", "atr", "mom", "usd_close"}, {"close", "volume", "atr", "mom", "gold_close"}, {"close", "volume", "atr", "usd_close", "gold_close"}, {"close", "volume", "mom", "usd_close", "gold_close"}, {"close", "macd", "rsi", "obv", "atr"}, {"close", "macd", "rsi", "obv", "mom"}, {"close", "macd", "rsi", "obv", "usd_close"}, {"close", "macd", "rsi", "obv", "gold_close"}, {"close", "macd", "rsi", "atr", "mom"}, {"close", "macd", "rsi", "atr", "usd_close"}, {"close", "macd", "rsi", "atr", "gold_close"}, {"close", "macd", "rsi", "mom", "usd_close"}, {"close", "macd", "rsi", "mom", "gold_close"}, {"close", "macd", "rsi", "usd_close", "gold_close"}, {"close", "macd", "obv", "atr", "mom"}, {"close", "macd", "obv", "atr", "usd_close"}, {"close", "macd", "obv", "atr", "gold_close"}, {"close", "macd", "obv", "mom", "usd_close"}, {"close", "macd", "obv", "mom", "gold_close"}, {"close", "macd", "obv", "usd_close", "gold_close"}, {"close", "macd", "atr", "mom", "usd_close"}, {"close", "macd", "atr", "mom", "gold_close"}, {"close", "macd", "atr", "usd_close", "gold_close"}, {"close", "macd", "mom", "usd_close", "gold_close"}, {"close", "rsi", "obv", "atr", "mom"}, {"close", "rsi", "obv", "atr", "usd_close"}, {"close", "rsi", "obv", "atr", "gold_close"}, {"close", "rsi", "obv", "mom", "usd_close"}, {"close", "rsi", "obv", "mom", "gold_close"}, {"close", "rsi", "obv", "usd_close", "gold_close"}, {"close", "rsi", "atr", "mom", "usd_close"}, {"close", "rsi", "atr", "mom", "gold_close"}, {"close", "rsi", "atr", "usd_close", "gold_close"}, {"close", "rsi", "mom", "usd_close", "gold_close"}, {"close", "obv", "atr", "mom", "usd_close"}, {"close", "obv", "atr", "mom", "gold_close"}, {"close", "obv", "atr", "usd_close", "gold_close"}, {"close", "obv", "mom", "usd_close", "gold_close"}, {"close", "atr", "mom", "usd_close", "gold_close"}, {"volume", "macd", "rsi", "obv", "atr"}, {"volume", "macd", "rsi", "obv", "mom"}, {"volume", "macd", "rsi", "obv", "usd_close"}, {"volume", "macd", "rsi", "obv", "gold_close"}, {"volume", "macd", "rsi", "atr", "mom"}, {"volume", "macd", "rsi", "atr", "usd_close"}, {"volume", "macd", "rsi", "atr", "gold_close"}, {"volume", "macd", "rsi", "mom", "usd_close"}, {"volume", "macd", "rsi", "mom", "gold_close"}, {"volume", "macd", "rsi", "usd_close", "gold_close"}, {"volume", "macd", "obv", "atr", "mom"}, {"volume", "macd", "obv", "atr", "usd_close"}, {"volume", "macd", "obv", "atr", "gold_close"}, {"volume", "macd", "obv", "mom", "usd_close"}, {"volume", "macd", "obv", "mom", "gold_close"}, {"volume", "macd", "obv", "usd_close", "gold_close"}, {"volume", "macd", "atr", "mom", "usd_close"}, {"volume", "macd", "atr", "mom", "gold_close"}, {"volume", "macd", "atr", "usd_close", "gold_close"}, {"volume", "macd", "mom", "usd_close", "gold_close"}, {"volume", "rsi", "obv", "atr", "mom"}, {"volume", "rsi", "obv", "atr", "usd_close"}, {"volume", "rsi", "obv", "atr", "gold_close"}, {"volume", "rsi", "obv", "mom", "usd_close"}, {"volume", "rsi", "obv", "mom", "gold_close"}, {"volume", "rsi", "obv", "usd_close", "gold_close"}, {"volume", "rsi", "atr", "mom", "usd_close"}, {"volume", "rsi", "atr", "mom", "gold_close"}, {"volume", "rsi", "atr", "usd_close", "gold_close"}, {"volume", "rsi", "mom", "usd_close", "gold_close"}, {"volume", "obv", "atr", "mom", "usd_close"}, {"volume", "obv", "atr", "mom", "gold_close"}, {"volume", "obv", "atr", "usd_close", "gold_close"}, {"volume", "obv", "mom", "usd_close", "gold_close"}, {"volume", "atr", "mom", "usd_close", "gold_close"}, {"macd", "rsi", "obv", "atr", "mom"}, {"macd", "rsi", "obv", "atr", "usd_close"}, {"macd", "rsi", "obv", "atr", "gold_close"}, {"macd", "rsi", "obv", "mom", "usd_close"}, {"macd", "rsi", "obv", "mom", "gold_close"}, {"macd", "rsi", "obv", "usd_close", "gold_close"}, {"macd", "rsi", "atr", "mom", "usd_close"}, {"macd", "rsi", "atr", "mom", "gold_close"}, {"macd", "rsi", "atr", "usd_close", "gold_close"}, {"macd", "rsi", "mom", "usd_close", "gold_close"}, {"macd", "obv", "atr", "mom", "usd_close"}, {"macd", "obv", "atr", "mom", "gold_close"}, {"macd", "obv", "atr", "usd_close", "gold_close"}, {"macd", "obv", "mom", "usd_close", "gold_close"}, {"macd", "atr", "mom", "usd_close", "gold_close"}, {"rsi", "obv", "atr", "mom", "usd_close"}, {"rsi", "obv", "atr", "mom", "gold_close"}, {"rsi", "obv", "atr", "usd_close", "gold_close"}, {"rsi", "obv", "mom", "usd_close", "gold_close"}, {"rsi", "atr", "mom", "usd_close", "gold_close"}, {"obv", "atr", "mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi", "obv", "atr"}, {"close", "volume", "macd", "rsi", "obv", "mom"}, {"close", "volume", "macd", "rsi", "obv", "usd_close"}, {"close", "volume", "macd", "rsi", "obv", "gold_close"}, {"close", "volume", "macd", "rsi", "atr", "mom"}, {"close", "volume", "macd", "rsi", "atr", "usd_close"}, {"close", "volume", "macd", "rsi", "atr", "gold_close"}, {"close", "volume", "macd", "rsi", "mom", "usd_close"}, {"close", "volume", "macd", "rsi", "mom", "gold_close"}, {"close", "volume", "macd", "rsi", "usd_close", "gold_close"}, {"close", "volume", "macd", "obv", "atr", "mom"}, {"close", "volume", "macd", "obv", "atr", "usd_close"}, {"close", "volume", "macd", "obv", "atr", "gold_close"}, {"close", "volume", "macd", "obv", "mom", "usd_close"}, {"close", "volume", "macd", "obv", "mom", "gold_close"}, {"close", "volume", "macd", "obv", "usd_close", "gold_close"}, {"close", "volume", "macd", "atr", "mom", "usd_close"}, {"close", "volume", "macd", "atr", "mom", "gold_close"}, {"close", "volume", "macd", "atr", "usd_close", "gold_close"}, {"close", "volume", "macd", "mom", "usd_close", "gold_close"}, {"close", "volume", "rsi", "obv", "atr", "mom"}, {"close", "volume", "rsi", "obv", "atr", "usd_close"}, {"close", "volume", "rsi", "obv", "atr", "gold_close"}, {"close", "volume", "rsi", "obv", "mom", "usd_close"}, {"close", "volume", "rsi", "obv", "mom", "gold_close"}, {"close", "volume", "rsi", "obv", "usd_close", "gold_close"}, {"close", "volume", "rsi", "atr", "mom", "usd_close"}, {"close", "volume", "rsi", "atr", "mom", "gold_close"}, {"close", "volume", "rsi", "atr", "usd_close", "gold_close"}, {"close", "volume", "rsi", "mom", "usd_close", "gold_close"}, {"close", "volume", "obv", "atr", "mom", "usd_close"}, {"close", "volume", "obv", "atr", "mom", "gold_close"}, {"close", "volume", "obv", "atr", "usd_close", "gold_close"}, {"close", "volume", "obv", "mom", "usd_close", "gold_close"}, {"close", "volume", "atr", "mom", "usd_close", "gold_close"}, {"close", "macd", "rsi", "obv", "atr", "mom"}, {"close", "macd", "rsi", "obv", "atr", "usd_close"}, {"close", "macd", "rsi", "obv", "atr", "gold_close"}, {"close", "macd", "rsi", "obv", "mom", "usd_close"}, {"close", "macd", "rsi", "obv", "mom", "gold_close"}, {"close", "macd", "rsi", "obv", "usd_close", "gold_close"}, {"close", "macd", "rsi", "atr", "mom", "usd_close"}, {"close", "macd", "rsi", "atr", "mom", "gold_close"}, {"close", "macd", "rsi", "atr", "usd_close", "gold_close"}, {"close", "macd", "rsi", "mom", "usd_close", "gold_close"}, {"close", "macd", "obv", "atr", "mom", "usd_close"}, {"close", "macd", "obv", "atr", "mom", "gold_close"}, {"close", "macd", "obv", "atr", "usd_close", "gold_close"}, {"close", "macd", "obv", "mom", "usd_close", "gold_close"}, {"close", "macd", "atr", "mom", "usd_close", "gold_close"}, {"close", "rsi", "obv", "atr", "mom", "usd_close"}, {"close", "rsi", "obv", "atr", "mom", "gold_close"}, {"close", "rsi", "obv", "atr", "usd_close", "gold_close"}, {"close", "rsi", "obv", "mom", "usd_close", "gold_close"}, {"close", "rsi", "atr", "mom", "usd_close", "gold_close"}, {"close", "obv", "atr", "mom", "usd_close", "gold_close"}, {"volume", "macd", "rsi", "obv", "atr", "mom"}, {"volume", "macd", "rsi", "obv", "atr", "usd_close"}, {"volume", "macd", "rsi", "obv", "atr", "gold_close"}, {"volume", "macd", "rsi", "obv", "mom", "usd_close"}, {"volume", "macd", "rsi", "obv", "mom", "gold_close"}, {"volume", "macd", "rsi", "obv", "usd_close", "gold_close"}, {"volume", "macd", "rsi", "atr", "mom", "usd_close"}, {"volume", "macd", "rsi", "atr", "mom", "gold_close"}, {"volume", "macd", "rsi", "atr", "usd_close", "gold_close"}, {"volume", "macd", "rsi", "mom", "usd_close", "gold_close"}, {"volume", "macd", "obv", "atr", "mom", "usd_close"}, {"volume", "macd", "obv", "atr", "mom", "gold_close"}, {"volume", "macd", "obv", "atr", "usd_close", "gold_close"}, {"volume", "macd", "obv", "mom", "usd_close", "gold_close"}, {"volume", "macd", "atr", "mom", "usd_close", "gold_close"}, {"volume", "rsi", "obv", "atr", "mom", "usd_close"}, {"volume", "rsi", "obv", "atr", "mom", "gold_close"}, {"volume", "rsi", "obv", "atr", "usd_close", "gold_close"}, {"volume", "rsi", "obv", "mom", "usd_close", "gold_close"}, {"volume", "rsi", "atr", "mom", "usd_close", "gold_close"}, {"volume", "obv", "atr", "mom", "usd_close", "gold_close"}, {"macd", "rsi", "obv", "atr", "mom", "usd_close"}, {"macd", "rsi", "obv", "atr", "mom", "gold_close"}, {"macd", "rsi", "obv", "atr", "usd_close", "gold_close"}, {"macd", "rsi", "obv", "mom", "usd_close", "gold_close"}, {"macd", "rsi", "atr", "mom", "usd_close", "gold_close"}, {"macd", "obv", "atr", "mom", "usd_close", "gold_close"}, {"rsi", "obv", "atr", "mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi", "obv", "atr", "mom"}, {"close", "volume", "macd", "rsi", "obv", "atr", "usd_close"}, {"close", "volume", "macd", "rsi", "obv", "atr", "gold_close"}, {"close", "volume", "macd", "rsi", "obv", "mom", "usd_close"}, {"close", "volume", "macd", "rsi", "obv", "mom", "gold_close"}, {"close", "volume", "macd", "rsi", "obv", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi", "atr", "mom", "usd_close"}, {"close", "volume", "macd", "rsi", "atr", "mom", "gold_close"}, {"close", "volume", "macd", "rsi", "atr", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi", "mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "obv", "atr", "mom", "usd_close"}, {"close", "volume", "macd", "obv", "atr", "mom", "gold_close"}, {"close", "volume", "macd", "obv", "atr", "usd_close", "gold_close"}, {"close", "volume", "macd", "obv", "mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "atr", "mom", "usd_close", "gold_close"}, {"close", "volume", "rsi", "obv", "atr", "mom", "usd_close"}, {"close", "volume", "rsi", "obv", "atr", "mom", "gold_close"}, {"close", "volume", "rsi", "obv", "atr", "usd_close", "gold_close"}, {"close", "volume", "rsi", "obv", "mom", "usd_close", "gold_close"}, {"close", "volume", "rsi", "atr", "mom", "usd_close", "gold_close"}, {"close", "volume", "obv", "atr", "mom", "usd_close", "gold_close"}, {"close", "macd", "rsi", "obv", "atr", "mom", "usd_close"}, {"close", "macd", "rsi", "obv", "atr", "mom", "gold_close"}, {"close", "macd", "rsi", "obv", "atr", "usd_close", "gold_close"}, {"close", "macd", "rsi", "obv", "mom", "usd_close", "gold_close"}, {"close", "macd", "rsi", "atr", "mom", "usd_close", "gold_close"}, {"close", "macd", "obv", "atr", "mom", "usd_close", "gold_close"}, {"close", "rsi", "obv", "atr", "mom", "usd_close", "gold_close"}, {"volume", "macd", "rsi", "obv", "atr", "mom", "usd_close"}, {"volume", "macd", "rsi", "obv", "atr", "mom", "gold_close"}, {"volume", "macd", "rsi", "obv", "atr", "usd_close", "gold_close"}, {"volume", "macd", "rsi", "obv", "mom", "usd_close", "gold_close"}, {"volume", "macd", "rsi", "atr", "mom", "usd_close", "gold_close"}, {"volume", "macd", "obv", "atr", "mom", "usd_close", "gold_close"}, {"volume", "rsi", "obv", "atr", "mom", "usd_close", "gold_close"}, {"macd", "rsi", "obv", "atr", "mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi", "obv", "atr", "mom", "usd_close"}, {"close", "volume", "macd", "rsi", "obv", "atr", "mom", "gold_close"}, {"close", "volume", "macd", "rsi", "obv", "atr", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi", "obv", "mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi", "atr", "mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "obv", "atr", "mom", "usd_close", "gold_close"}, {"close", "volume", "rsi", "obv", "atr", "mom", "usd_close", "gold_close"}, {"close", "macd", "rsi", "obv", "atr", "mom", "usd_close", "gold_close"}, {"volume", "macd", "rsi", "obv", "atr", "mom", "usd_close", "gold_close"}, {"close", "volume", "macd", "rsi", "obv", "atr", "mom", "usd_close", "gold_close"}};

    public static int[][] powerSetNum = {{0},{1},{2},{3},{4},{5},{6},{7},{8},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{2,3},{2,4},{2,5},{2,6},{2,7},{2,8},{3,4},{3,5},{3,6},{3,7},{3,8},{4,5},{4,6},{4,7},{4,8},{5,6},{5,7},{5,8},{6,7},{6,8},{7,8},{0,1,2},{0,1,3},{0,1,4},{0,1,5},{0,1,6},{0,1,7},{0,1,8},{0,2,3},{0,2,4},{0,2,5},{0,2,6},{0,2,7},{0,2,8},{0,3,4},{0,3,5},{0,3,6},{0,3,7},{0,3,8},{0,4,5},{0,4,6},{0,4,7},{0,4,8},{0,5,6},{0,5,7},{0,5,8},{0,6,7},{0,6,8},{0,7,8},{1,2,3},{1,2,4},{1,2,5},{1,2,6},{1,2,7},{1,2,8},{1,3,4},{1,3,5},{1,3,6},{1,3,7},{1,3,8},{1,4,5},{1,4,6},{1,4,7},{1,4,8},{1,5,6},{1,5,7},{1,5,8},{1,6,7},{1,6,8},{1,7,8},{2,3,4},{2,3,5},{2,3,6},{2,3,7},{2,3,8},{2,4,5},{2,4,6},{2,4,7},{2,4,8},{2,5,6},{2,5,7},{2,5,8},{2,6,7},{2,6,8},{2,7,8},{3,4,5},{3,4,6},{3,4,7},{3,4,8},{3,5,6},{3,5,7},{3,5,8},{3,6,7},{3,6,8},{3,7,8},{4,5,6},{4,5,7},{4,5,8},{4,6,7},{4,6,8},{4,7,8},{5,6,7},{5,6,8},{5,7,8},{6,7,8},{0,1,2,3},{0,1,2,4},{0,1,2,5},{0,1,2,6},{0,1,2,7},{0,1,2,8},{0,1,3,4},{0,1,3,5},{0,1,3,6},{0,1,3,7},{0,1,3,8},{0,1,4,5},{0,1,4,6},{0,1,4,7},{0,1,4,8},{0,1,5,6},{0,1,5,7},{0,1,5,8},{0,1,6,7},{0,1,6,8},{0,1,7,8},{0,2,3,4},{0,2,3,5},{0,2,3,6},{0,2,3,7},{0,2,3,8},{0,2,4,5},{0,2,4,6},{0,2,4,7},{0,2,4,8},{0,2,5,6},{0,2,5,7},{0,2,5,8},{0,2,6,7},{0,2,6,8},{0,2,7,8},{0,3,4,5},{0,3,4,6},{0,3,4,7},{0,3,4,8},{0,3,5,6},{0,3,5,7},{0,3,5,8},{0,3,6,7},{0,3,6,8},{0,3,7,8},{0,4,5,6},{0,4,5,7},{0,4,5,8},{0,4,6,7},{0,4,6,8},{0,4,7,8},{0,5,6,7},{0,5,6,8},{0,5,7,8},{0,6,7,8},{1,2,3,4},{1,2,3,5},{1,2,3,6},{1,2,3,7},{1,2,3,8},{1,2,4,5},{1,2,4,6},{1,2,4,7},{1,2,4,8},{1,2,5,6},{1,2,5,7},{1,2,5,8},{1,2,6,7},{1,2,6,8},{1,2,7,8},{1,3,4,5},{1,3,4,6},{1,3,4,7},{1,3,4,8},{1,3,5,6},{1,3,5,7},{1,3,5,8},{1,3,6,7},{1,3,6,8},{1,3,7,8},{1,4,5,6},{1,4,5,7},{1,4,5,8},{1,4,6,7},{1,4,6,8},{1,4,7,8},{1,5,6,7},{1,5,6,8},{1,5,7,8},{1,6,7,8},{2,3,4,5},{2,3,4,6},{2,3,4,7},{2,3,4,8},{2,3,5,6},{2,3,5,7},{2,3,5,8},{2,3,6,7},{2,3,6,8},{2,3,7,8},{2,4,5,6},{2,4,5,7},{2,4,5,8},{2,4,6,7},{2,4,6,8},{2,4,7,8},{2,5,6,7},{2,5,6,8},{2,5,7,8},{2,6,7,8},{3,4,5,6},{3,4,5,7},{3,4,5,8},{3,4,6,7},{3,4,6,8},{3,4,7,8},{3,5,6,7},{3,5,6,8},{3,5,7,8},{3,6,7,8},{4,5,6,7},{4,5,6,8},{4,5,7,8},{4,6,7,8},{5,6,7,8},{0,1,2,3,4},{0,1,2,3,5},{0,1,2,3,6},{0,1,2,3,7},{0,1,2,3,8},{0,1,2,4,5},{0,1,2,4,6},{0,1,2,4,7},{0,1,2,4,8},{0,1,2,5,6},{0,1,2,5,7},{0,1,2,5,8},{0,1,2,6,7},{0,1,2,6,8},{0,1,2,7,8},{0,1,3,4,5},{0,1,3,4,6},{0,1,3,4,7},{0,1,3,4,8},{0,1,3,5,6},{0,1,3,5,7},{0,1,3,5,8},{0,1,3,6,7},{0,1,3,6,8},{0,1,3,7,8},{0,1,4,5,6},{0,1,4,5,7},{0,1,4,5,8},{0,1,4,6,7},{0,1,4,6,8},{0,1,4,7,8},{0,1,5,6,7},{0,1,5,6,8},{0,1,5,7,8},{0,1,6,7,8},{0,2,3,4,5},{0,2,3,4,6},{0,2,3,4,7},{0,2,3,4,8},{0,2,3,5,6},{0,2,3,5,7},{0,2,3,5,8},{0,2,3,6,7},{0,2,3,6,8},{0,2,3,7,8},{0,2,4,5,6},{0,2,4,5,7},{0,2,4,5,8},{0,2,4,6,7},{0,2,4,6,8},{0,2,4,7,8},{0,2,5,6,7},{0,2,5,6,8},{0,2,5,7,8},{0,2,6,7,8},{0,3,4,5,6},{0,3,4,5,7},{0,3,4,5,8},{0,3,4,6,7},{0,3,4,6,8},{0,3,4,7,8},{0,3,5,6,7},{0,3,5,6,8},{0,3,5,7,8},{0,3,6,7,8},{0,4,5,6,7},{0,4,5,6,8},{0,4,5,7,8},{0,4,6,7,8},{0,5,6,7,8},{1,2,3,4,5},{1,2,3,4,6},{1,2,3,4,7},{1,2,3,4,8},{1,2,3,5,6},{1,2,3,5,7},{1,2,3,5,8},{1,2,3,6,7},{1,2,3,6,8},{1,2,3,7,8},{1,2,4,5,6},{1,2,4,5,7},{1,2,4,5,8},{1,2,4,6,7},{1,2,4,6,8},{1,2,4,7,8},{1,2,5,6,7},{1,2,5,6,8},{1,2,5,7,8},{1,2,6,7,8},{1,3,4,5,6},{1,3,4,5,7},{1,3,4,5,8},{1,3,4,6,7},{1,3,4,6,8},{1,3,4,7,8},{1,3,5,6,7},{1,3,5,6,8},{1,3,5,7,8},{1,3,6,7,8},{1,4,5,6,7},{1,4,5,6,8},{1,4,5,7,8},{1,4,6,7,8},{1,5,6,7,8},{2,3,4,5,6},{2,3,4,5,7},{2,3,4,5,8},{2,3,4,6,7},{2,3,4,6,8},{2,3,4,7,8},{2,3,5,6,7},{2,3,5,6,8},{2,3,5,7,8},{2,3,6,7,8},{2,4,5,6,7},{2,4,5,6,8},{2,4,5,7,8},{2,4,6,7,8},{2,5,6,7,8},{3,4,5,6,7},{3,4,5,6,8},{3,4,5,7,8},{3,4,6,7,8},{3,5,6,7,8},{4,5,6,7,8},{0,1,2,3,4,5},{0,1,2,3,4,6},{0,1,2,3,4,7},{0,1,2,3,4,8},{0,1,2,3,5,6},{0,1,2,3,5,7},{0,1,2,3,5,8},{0,1,2,3,6,7},{0,1,2,3,6,8},{0,1,2,3,7,8},{0,1,2,4,5,6},{0,1,2,4,5,7},{0,1,2,4,5,8},{0,1,2,4,6,7},{0,1,2,4,6,8},{0,1,2,4,7,8},{0,1,2,5,6,7},{0,1,2,5,6,8},{0,1,2,5,7,8},{0,1,2,6,7,8},{0,1,3,4,5,6},{0,1,3,4,5,7},{0,1,3,4,5,8},{0,1,3,4,6,7},{0,1,3,4,6,8},{0,1,3,4,7,8},{0,1,3,5,6,7},{0,1,3,5,6,8},{0,1,3,5,7,8},{0,1,3,6,7,8},{0,1,4,5,6,7},{0,1,4,5,6,8},{0,1,4,5,7,8},{0,1,4,6,7,8},{0,1,5,6,7,8},{0,2,3,4,5,6},{0,2,3,4,5,7},{0,2,3,4,5,8},{0,2,3,4,6,7},{0,2,3,4,6,8},{0,2,3,4,7,8},{0,2,3,5,6,7},{0,2,3,5,6,8},{0,2,3,5,7,8},{0,2,3,6,7,8},{0,2,4,5,6,7},{0,2,4,5,6,8},{0,2,4,5,7,8},{0,2,4,6,7,8},{0,2,5,6,7,8},{0,3,4,5,6,7},{0,3,4,5,6,8},{0,3,4,5,7,8},{0,3,4,6,7,8},{0,3,5,6,7,8},{0,4,5,6,7,8},{1,2,3,4,5,6},{1,2,3,4,5,7},{1,2,3,4,5,8},{1,2,3,4,6,7},{1,2,3,4,6,8},{1,2,3,4,7,8},{1,2,3,5,6,7},{1,2,3,5,6,8},{1,2,3,5,7,8},{1,2,3,6,7,8},{1,2,4,5,6,7},{1,2,4,5,6,8},{1,2,4,5,7,8},{1,2,4,6,7,8},{1,2,5,6,7,8},{1,3,4,5,6,7},{1,3,4,5,6,8},{1,3,4,5,7,8},{1,3,4,6,7,8},{1,3,5,6,7,8},{1,4,5,6,7,8},{2,3,4,5,6,7},{2,3,4,5,6,8},{2,3,4,5,7,8},{2,3,4,6,7,8},{2,3,5,6,7,8},{2,4,5,6,7,8},{3,4,5,6,7,8},{0,1,2,3,4,5,6},{0,1,2,3,4,5,7},{0,1,2,3,4,5,8},{0,1,2,3,4,6,7},{0,1,2,3,4,6,8},{0,1,2,3,4,7,8},{0,1,2,3,5,6,7},{0,1,2,3,5,6,8},{0,1,2,3,5,7,8},{0,1,2,3,6,7,8},{0,1,2,4,5,6,7},{0,1,2,4,5,6,8},{0,1,2,4,5,7,8},{0,1,2,4,6,7,8},{0,1,2,5,6,7,8},{0,1,3,4,5,6,7},{0,1,3,4,5,6,8},{0,1,3,4,5,7,8},{0,1,3,4,6,7,8},{0,1,3,5,6,7,8},{0,1,4,5,6,7,8},{0,2,3,4,5,6,7},{0,2,3,4,5,6,8},{0,2,3,4,5,7,8},{0,2,3,4,6,7,8},{0,2,3,5,6,7,8},{0,2,4,5,6,7,8},{0,3,4,5,6,7,8},{1,2,3,4,5,6,7},{1,2,3,4,5,6,8},{1,2,3,4,5,7,8},{1,2,3,4,6,7,8},{1,2,3,5,6,7,8},{1,2,4,5,6,7,8},{1,3,4,5,6,7,8},{2,3,4,5,6,7,8},{0,1,2,3,4,5,6,7},{0,1,2,3,4,5,6,8},{0,1,2,3,4,5,7,8},{0,1,2,3,4,6,7,8},{0,1,2,3,5,6,7,8},{0,1,2,4,5,6,7,8},{0,1,3,4,5,6,7,8},{0,2,3,4,5,6,7,8},{1,2,3,4,5,6,7,8},{0,1,2,3,4,5,6,7,8}};
}
