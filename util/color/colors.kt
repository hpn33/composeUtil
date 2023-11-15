package util.color

import androidx.compose.ui.graphics.Color


object Colors {


    val transparent = Color(0x00000000);

    val black = Color(0xFF000000);
    val black87 = Color(0xDD000000);
    val black54 = Color(0x8A000000);
    val black45 = Color(0x73000000);
    val black38 = Color(0x61000000);
    val black26 = Color(0x42000000)
    val black12 = Color(0x1F000000);


    val white = Color(0xFFFFFFFF);
    val white70 = Color(0xB3FFFFFF);
    val white60 = Color(0x99FFFFFF);
    val white54 = Color(0x8AFFFFFF);
    val white38 = Color(0x62FFFFFF);
    val white30 = Color(0x4DFFFFFF);
    val white24 = Color(0x3DFFFFFF);
    val white12 = Color(0x1FFFFFFF);
    val white10 = Color(0x1AFFFFFF);


    val _redPrimaryValue = 0xFFF44336;
    val red = MaterialColor(
        mapOf(
            50 to Color(0xFFFFEBEE),
            100 to Color(0xFFFFCDD2),
            200 to Color(0xFFEF9A9A),
            300 to Color(0xFFE57373),
            400 to Color(0xFFEF5350),
            500 to Color(_redPrimaryValue),
            600 to Color(0xFFE53935),
            700 to Color(0xFFD32F2F),
            800 to Color(0xFFC62828),
            900 to Color(0xFFB71C1C),
        )
    )


    val _redAccentValue = 0xFFFF5252;
    val redAccent = MaterialAccentColor(
        mapOf(
            100 to Color(0xFFFF8A80),
            200 to Color(_redAccentValue),
            400 to Color(0xFFFF1744),
            700 to Color(0xFFD50000),
        )
    );

    val _pinkPrimaryValue = 0xFFE91E63;
    val pink = MaterialColor(
        mapOf(
            50 to Color(0xFFFCE4EC),
            100 to Color(0xFFF8BBD0),
            200 to Color(0xFFF48FB1),
            300 to Color(0xFFF06292),
            400 to Color(0xFFEC407A),
            500 to Color(_pinkPrimaryValue),
            600 to Color(0xFFD81B60),
            700 to Color(0xFFC2185B),
            800 to Color(0xFFAD1457),
            900 to Color(0xFF880E4F),
        )
    );

    val _pinkAccentPrimaryValue = 0xFFFF4081;
    val pinkAccent = MaterialAccentColor(
        mapOf(
            100 to Color(0xFFFF80AB),
            200 to Color(_pinkAccentPrimaryValue),
            400 to Color(0xFFF50057),
            700 to Color(0xFFC51162),
        )
    );

    val _purplePrimaryValue = 0xFF9C27B0;
    val purple = MaterialColor(
        mapOf(
            50 to Color(0xFFF3E5F5),
            100 to Color(0xFFE1BEE7),
            200 to Color(0xFFCE93D8),
            300 to Color(0xFFBA68C8),
            400 to Color(0xFFAB47BC),
            500 to Color(_purplePrimaryValue),
            600 to Color(0xFF8E24AA),
            700 to Color(0xFF7B1FA2),
            800 to Color(0xFF6A1B9A),
            900 to Color(0xFF4A148C),
        )
    );


    val _purpleAccentPrimaryValue = 0xFFE040FB;
    val purpleAccent = MaterialAccentColor(
        mapOf(
            100 to Color(0xFFEA80FC),
            200 to Color(_purpleAccentPrimaryValue),
            400 to Color(0xFFD500F9),
            700 to Color(0xFFAA00FF),
        )
    );


    val _deepPurplePrimaryValue = 0xFF673AB7;
    val deepPurple = MaterialColor(
        mapOf(
            50 to Color(0xFFEDE7F6),
            100 to Color(0xFFD1C4E9),
            200 to Color(0xFFB39DDB),
            300 to Color(0xFF9575CD),
            400 to Color(0xFF7E57C2),
            500 to Color(_deepPurplePrimaryValue),
            600 to Color(0xFF5E35B1),
            700 to Color(0xFF512DA8),
            800 to Color(0xFF4527A0),
            900 to Color(0xFF311B92),
        )
    );


    val _deepPurpleAccentPrimaryValue = 0xFF7C4DFF;
    val deepPurpleAccent = MaterialAccentColor(
        mapOf(
            100 to Color(0xFFB388FF),
            200 to Color(_deepPurpleAccentPrimaryValue),
            400 to Color(0xFF651FFF),
            700 to Color(0xFF6200EA),
        )
    );


    val _indigoPrimaryValue = 0xFF3F51B5;
    val indigo = MaterialColor(
        mapOf(
            50 to Color(0xFFE8EAF6),
            100 to Color(0xFFC5CAE9),
            200 to Color(0xFF9FA8DA),
            300 to Color(0xFF7986CB),
            400 to Color(0xFF5C6BC0),
            500 to Color(_indigoPrimaryValue),
            600 to Color(0xFF3949AB),
            700 to Color(0xFF303F9F),
            800 to Color(0xFF283593),
            900 to Color(0xFF1A237E),
        )
    );

    val _indigoAccentPrimaryValue = 0xFF536DFE;
    val indigoAccent = MaterialAccentColor(
        mapOf(
            100 to Color(0xFF8C9EFF),
            200 to Color(_indigoAccentPrimaryValue),
            400 to Color(0xFF3D5AFE),
            700 to Color(0xFF304FFE),
        )
    );

    val _bluePrimaryValue = 0xFF2196F3;
    val blue = MaterialColor(
        mapOf(
            50 to Color(0xFFE3F2FD),
            100 to Color(0xFFBBDEFB),
            200 to Color(0xFF90CAF9),
            300 to Color(0xFF64B5F6),
            400 to Color(0xFF42A5F5),
            500 to Color(_bluePrimaryValue),
            600 to Color(0xFF1E88E5),
            700 to Color(0xFF1976D2),
            800 to Color(0xFF1565C0),
            900 to Color(0xFF0D47A1),
        )
    );


    val _blueAccentPrimaryValue = 0xFF448AFF;
    val blueAccent = MaterialAccentColor(
        mapOf(
            100 to Color(0xFF82B1FF),
            200 to Color(_blueAccentPrimaryValue),
            400 to Color(0xFF2979FF),
            700 to Color(0xFF2962FF),
        )
    );

    val _lightBluePrimaryValue = 0xFF03A9F4;
    val lightBlue = MaterialColor(
        mapOf(
            50 to Color(0xFFE1F5FE),
            100 to Color(0xFFB3E5FC),
            200 to Color(0xFF81D4FA),
            300 to Color(0xFF4FC3F7),
            400 to Color(0xFF29B6F6),
            500 to Color(_lightBluePrimaryValue),
            600 to Color(0xFF039BE5),
            700 to Color(0xFF0288D1),
            800 to Color(0xFF0277BD),
            900 to Color(0xFF01579B),
        )
    );


    val _lightBlueAccentPrimaryValue = 0xFF40C4FF;
    val lightBlueAccent = MaterialAccentColor(
        mapOf(
            100 to Color(0xFF80D8FF),
            200 to Color(_lightBlueAccentPrimaryValue),
            400 to Color(0xFF00B0FF),
            700 to Color(0xFF0091EA),
        )
    );


    val _cyanPrimaryValue = 0xFF00BCD4;
    val cyan = MaterialColor(
        mapOf(
            50 to Color(0xFFE0F7FA),
            100 to Color(0xFFB2EBF2),
            200 to Color(0xFF80DEEA),
            300 to Color(0xFF4DD0E1),
            400 to Color(0xFF26C6DA),
            500 to Color(_cyanPrimaryValue),
            600 to Color(0xFF00ACC1),
            700 to Color(0xFF0097A7),
            800 to Color(0xFF00838F),
            900 to Color(0xFF006064),
        )
    );

    val _cyanAccentPrimaryValue = 0xFF18FFFF;
    val cyanAccent = MaterialAccentColor(
        mapOf(
            100 to Color(0xFF84FFFF),
            200 to Color(_cyanAccentPrimaryValue),
            400 to Color(0xFF00E5FF),
            700 to Color(0xFF00B8D4),
        )
    );

    val _tealPrimaryValue = 0xFF009688;
    val teal = MaterialColor(
        mapOf(
            50 to Color(0xFFE0F2F1),
            100 to Color(0xFFB2DFDB),
            200 to Color(0xFF80CBC4),
            300 to Color(0xFF4DB6AC),
            400 to Color(0xFF26A69A),
            500 to Color(_tealPrimaryValue),
            600 to Color(0xFF00897B),
            700 to Color(0xFF00796B),
            800 to Color(0xFF00695C),
            900 to Color(0xFF004D40),
        )
    );


    val _tealAccentPrimaryValue = 0xFF64FFDA;
    val tealAccent =
        MaterialAccentColor(
            mapOf(
                100 to Color(
                    0xFFA7FFEB
                ),
                200 to Color(
                    _tealAccentPrimaryValue
                ),
                400 to Color(
                    0xFF1DE9B6
                ),
                700 to Color(
                    0xFF00BFA5
                ),
            )
        );


    val _greenPrimaryValue = 0xFF4CAF50;
    val green = MaterialColor(
        mapOf(
            50 to Color(0xFFE8F5E9),
            100 to Color(
                0xFFC8E6C9
            ),
            200 to Color(
                0xFFA5D6A7
            ),
            300 to Color(
                0xFF81C784
            ),
            400 to Color(
                0xFF66BB6A
            ),
            500 to Color(
                _greenPrimaryValue
            ),
            600 to Color(
                0xFF43A047
            ),
            700 to Color(
                0xFF388E3C
            ),
            800 to Color(
                0xFF2E7D32
            ),
            900 to Color(
                0xFF1B5E20
            ),
        )
    );


    val _greenAccentPrimaryValue = 0xFF69F0AE;
    val greenAccent =
        MaterialAccentColor(
            mapOf(
                100 to Color(
                    0xFFB9F6CA
                ),
                200 to Color(
                    _greenAccentPrimaryValue
                ),
                400 to Color(
                    0xFF00E676
                ),
                700 to Color(
                    0xFF00C853
                ),
            )
        );


    val _lightGreenPrimaryValue = 0xFF8BC34A;
    val lightGreen =
        MaterialColor(
            mapOf(
                50 to Color(
                    0xFFF1F8E9
                ),
                100 to Color(
                    0xFFDCEDC8
                ),
                200 to Color(
                    0xFFC5E1A5
                ),
                300 to Color(
                    0xFFAED581
                ),
                400 to Color(
                    0xFF9CCC65
                ),
                500 to Color(
                    _lightGreenPrimaryValue
                ),
                600 to Color(
                    0xFF7CB342
                ),
                700 to Color(
                    0xFF689F38
                ),
                800 to Color(
                    0xFF558B2F
                ),
                900 to Color(
                    0xFF33691E
                ),
            )
        );


    val _lightGreenAccentPrimaryValue = 0xFFB2FF59;
    val lightGreenAccent =
        MaterialAccentColor(
            mapOf(

                100 to Color(
                    0xFFCCFF90
                ),
                200 to Color(
                    _lightGreenAccentPrimaryValue
                ),
                400 to Color(
                    0xFF76FF03
                ),
                700 to Color(
                    0xFF64DD17
                ),
            )
        );


    val _limePrimaryValue = 0xFFCDDC39;
    val lime = MaterialColor(
        mapOf(

            50 to Color(
                0xFFF9FBE7
            ),
            100 to Color(
                0xFFF0F4C3
            ),
            200 to Color(
                0xFFE6EE9C
            ),
            300 to Color(
                0xFFDCE775
            ),
            400 to Color(
                0xFFD4E157
            ),
            500 to Color(
                _limePrimaryValue
            ),
            600 to Color(
                0xFFC0CA33
            ),
            700 to Color(
                0xFFAFB42B
            ),
            800 to Color(
                0xFF9E9D24
            ),
            900 to Color(
                0xFF827717
            ),
        )
    );


    val _limeAccentPrimaryValue = 0xFFEEFF41;
    val limeAccent = MaterialAccentColor(
        mapOf(

            100 to Color(
                0xFFF4FF81
            ),
            200 to Color(
                _limeAccentPrimaryValue
            ),
            400 to Color(
                0xFFC6FF00
            ),
            700 to Color(
                0xFFAEEA00
            ),
        )
    );


    val _yellowPrimaryValue = 0xFFFFEB3B;
    val yellow = MaterialColor(
        mapOf(

            50 to Color(
                0xFFFFFDE7
            ),
            100 to Color(
                0xFFFFF9C4
            ),
            200 to Color(
                0xFFFFF59D
            ),
            300 to Color(
                0xFFFFF176
            ),
            400 to Color(
                0xFFFFEE58
            ),
            500 to Color(
                _yellowPrimaryValue
            ),
            600 to Color(
                0xFFFDD835
            ),
            700 to Color(
                0xFFFBC02D
            ),
            800 to Color(
                0xFFF9A825
            ),
            900 to Color(
                0xFFF57F17
            ),
        )
    );


    val _yellowAccentPrimaryValue = 0xFFFFFF00;
    val yellowAccent = MaterialAccentColor(
        mapOf(

            100 to Color(
                0xFFFFFF8D
            ),
            200 to Color(
                _yellowAccentPrimaryValue
            ),
            400 to Color(
                0xFFFFEA00
            ),
            700 to Color(
                0xFFFFD600
            ),
        )
    );


    val _amberPrimaryValue = 0xFFFFC107;
    val amber = MaterialColor(
        mapOf(

            50 to Color(
                0xFFFFF8E1
            ),
            100 to Color(
                0xFFFFECB3
            ),
            200 to Color(
                0xFFFFE082
            ),
            300 to Color(
                0xFFFFD54F
            ),
            400 to Color(
                0xFFFFCA28
            ),
            500 to Color(
                _amberPrimaryValue
            ),
            600 to Color(
                0xFFFFB300
            ),
            700 to Color(
                0xFFFFA000
            ),
            800 to Color(
                0xFFFF8F00
            ),
            900 to Color(
                0xFFFF6F00
            ),
        )
    );


    val _amberAccentPrimaryValue = 0xFFFFD740;
    val amberAccent = MaterialAccentColor(
        mapOf(

            100 to Color(
                0xFFFFE57F
            ),
            200 to Color(
                _amberAccentPrimaryValue
            ),
            400 to Color(
                0xFFFFC400
            ),
            700 to Color(
                0xFFFFAB00
            ),
        )
    );


    val _orangePrimaryValue = 0xFFFF9800;
    val orange = MaterialColor(
        mapOf(

            50 to Color(
                0xFFFFF3E0
            ),
            100 to Color(
                0xFFFFE0B2
            ),
            200 to Color(
                0xFFFFCC80
            ),
            300 to Color(
                0xFFFFB74D
            ),
            400 to Color(
                0xFFFFA726
            ),
            500 to Color(
                _orangePrimaryValue
            ),
            600 to Color(
                0xFFFB8C00
            ),
            700 to Color(
                0xFFF57C00
            ),
            800 to Color(
                0xFFEF6C00
            ),
            900 to Color(
                0xFFE65100
            ),
        )
    );


    val _orangeAccentPrimaryValue = 0xFFFFAB40;
    val orangeAccent = MaterialAccentColor(
        mapOf(

            100 to Color(
                0xFFFFD180
            ),
            200 to Color(
                _orangeAccentPrimaryValue
            ),
            400 to Color(
                0xFFFF9100
            ),
            700 to Color(
                0xFFFF6D00
            ),
        )
    );


    val _deepOrangePrimaryValue = 0xFFFF5722;
    val deepOrange = MaterialColor(
        mapOf(

            50 to Color(
                0xFFFBE9E7
            ),
            100 to Color(
                0xFFFFCCBC
            ),
            200 to Color(
                0xFFFFAB91
            ),
            300 to Color(
                0xFFFF8A65
            ),
            400 to Color(
                0xFFFF7043
            ),
            500 to Color(
                _deepOrangePrimaryValue
            ),
            600 to Color(
                0xFFF4511E
            ),
            700 to Color(
                0xFFE64A19
            ),
            800 to Color(
                0xFFD84315
            ),
            900 to Color(
                0xFFBF360C
            ),
        )
    );


    val _deepOrangeAccentPrimaryValue = 0xFFFF6E40;
    val deepOrangeAccent = MaterialAccentColor(
        mapOf(

            100 to Color(
                0xFFFF9E80
            ),
            200 to Color(
                _deepOrangeAccentPrimaryValue
            ),
            400 to Color(
                0xFFFF3D00
            ),
            700 to Color(
                0xFFDD2C00
            ),
        )
    );

    val _brownPrimaryValue = 0xFF795548;
    val brown = MaterialColor(
        mapOf(

            50 to Color(
                0xFFEFEBE9
            ),
            100 to Color(
                0xFFD7CCC8
            ),
            200 to Color(
                0xFFBCAAA4
            ),
            300 to Color(
                0xFFA1887F
            ),
            400 to Color(
                0xFF8D6E63
            ),
            500 to Color(
                _brownPrimaryValue
            ),
            600 to Color(
                0xFF6D4C41
            ),
            700 to Color(
                0xFF5D4037
            ),
            800 to Color(
                0xFF4E342E
            ),
            900 to Color(
                0xFF3E2723
            ),
        )
    );


    val _greyPrimaryValue = 0xFF9E9E9E;
    val grey = MaterialColor(
        mapOf(

            50 to Color(
                0xFFFAFAFA
            ),
            100 to Color(
                0xFFF5F5F5
            ),
            200 to Color(
                0xFFEEEEEE
            ),
            300 to Color(
                0xFFE0E0E0
            ),
            350 to Color(
                0xFFD6D6D6
            ), // only for raised button while pressed in light theme
            400 to Color(
                0xFFBDBDBD
            ),
            500 to Color(
                _greyPrimaryValue
            ),
            600 to Color(
                0xFF757575
            ),
            700 to Color(
                0xFF616161
            ),
            800 to Color(
                0xFF424242
            ),
            850 to Color(
                0xFF303030
            ), // only for background color in dark theme
            900 to Color(
                0xFF212121
            ),
        )
    );

    val _blueGreyPrimaryValue = 0xFF607D8B;
    val blueGrey = MaterialColor(
        mapOf(

            50 to Color(
                0xFFECEFF1
            ),
            100 to Color(
                0xFFCFD8DC
            ),
            200 to Color(
                0xFFB0BEC5
            ),
            300 to Color(
                0xFF90A4AE
            ),
            400 to Color(
                0xFF78909C
            ),
            500 to Color(
                _blueGreyPrimaryValue
            ),
            600 to Color(
                0xFF546E7A
            ),
            700 to Color(
                0xFF455A64
            ),
            800 to Color(
                0xFF37474F
            ),
            900 to Color(
                0xFF263238
            ),
        )
    );

    val primaries = listOf(
        red,
        pink,
        purple,
        deepPurple,
        indigo,
        blue,
        lightBlue,
        cyan,
        teal,
        green,
        lightGreen,
        lime,
        yellow,
        amber,
        orange,
        deepOrange,
        brown,
        blueGrey,
    )

    val accents = listOf(
        redAccent,
        pinkAccent,
        purpleAccent,
        deepPurpleAccent,
        indigoAccent,
        blueAccent,
        lightBlueAccent,
        cyanAccent,
        tealAccent,
        greenAccent,
        lightGreenAccent,
        limeAccent,
        yellowAccent,
        amberAccent,
        orangeAccent,
        deepOrangeAccent,
    )
}
