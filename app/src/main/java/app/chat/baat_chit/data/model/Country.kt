package app.chat.baat_chit.data.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


data class Country(
    val name: String,
    val code: String,
    val icon: MutableState<Boolean> = mutableStateOf(false),
    var selected: MutableState<Boolean> = mutableStateOf(false)
)


val countries = listOf(
    Country("Afghanistan", "+93"),
    Country("Albania", "+355"),
    Country("Algeria", "+213"),
    Country("American Samoa", "+1-684"),
    Country("Andorra", "+376"),
    Country("Angola", "+244"),
    Country("Anguilla", "+1-264"),
    Country("Antarctica", "+672"),
    Country("Antigua and Barbuda", "+1-268"),
    Country("Argentina", "+54"),
    Country("Armenia", "+374"),
    Country("Aruba", "+297"),
    Country("Australia", "+61"),
    Country("Austria", "+43"),
    Country("Azerbaijan", "+994"),
    Country("Bahamas", "+1-242"),
    Country("Bahrain", "+973"),
    Country("Bangladesh", "+880"),
    Country("Barbados", "+1-246"),
    Country("Belarus", "+375"),
    Country("Belgium", "+32"),
    Country("Belize", "+501"),
    Country("Benin", "+229"),
    Country("Bermuda", "+1-441"),
    Country("Bhutan", "+975"),
    Country("Bolivia", "+591"),
    Country("Bosnia and Herzegovina", "+387"),
    Country("Botswana", "+267"),
    Country("Brazil", "+55"),
    Country("British Indian Ocean Territory", "+246"),
    Country("British Virgin Islands", "+1-284"),
    Country("Brunei", "+673"),
    Country("Bulgaria", "+359"),
    Country("Burkina Faso", "+226"),
    Country("Burundi", "+257"),
    Country("Cambodia", "+855"),
    Country("Cameroon", "+237"),
    Country("Canada", "+1"),
    Country("Cape Verde", "+238"),
    Country("Cayman Islands", "+1-345"),
    Country("Central African Republic", "+236"),
    Country("Chad", "+235"),
    Country("Chile", "+56"),
    Country("China", "+86"),
    Country("Christmas Island", "+61"),
    Country("Cocos Islands", "+61"),
    Country("Colombia", "+57"),
    Country("Comoros", "+269"),
    Country("Cook Islands", "+682"),
    Country("Costa Rica", "+506"),
    Country("Croatia", "+385"),
    Country("Cuba", "+53"),
    Country("Curacao", "+599"),
    Country("Cyprus", "+357"),
    Country("Czech Republic", "+420"),
    Country("Democratic Republic of the Congo", "+243"),
    Country("Denmark", "+45"),
    Country("Djibouti", "+253"),
    Country("Dominica", "+1-767"),
    Country("Dominican Republic", "+1-809, 1-829, 1-849"),
    Country("East Timor", "+670"),
    Country("Ecuador", "+593"),
    Country("Egypt", "+20"),
    Country("El Salvador", "+503"),
    Country("Equatorial Guinea", "+240"),
    Country("Eritrea", "+291"),
    Country("Estonia", "+372"),
    Country("Ethiopia", "+251"),
    Country("Falkland Islands", "+500"),
    Country("Faroe Islands", "+298"),
    Country("Fiji", "+679"),
    Country("Finland", "+358"),
    Country("France", "+33"),
    Country("French Polynesia", "+689"),
    Country("Gabon", "+241"),
    Country("Gambia", "+220"),
    Country("Georgia", "+995"),
    Country("Germany", "+49"),
    Country("Ghana", "+233"),
    Country("Gibraltar", "+350"),
    Country("Greece", "+30"),
    Country("Greenland", "+299"),
    Country("Grenada", "+1-473"),
    Country("Guam", "+1-671"),
    Country("Guatemala", "+502"),
    Country("Guernsey", "+44-1481"),
    Country("Guinea", "+224"),
    Country("Guinea-Bissau", "+245"),
    Country("Guyana", "+592"),
    Country("Haiti", "+509"),
    Country("Honduras", "+504"),
    Country("Hong Kong", "+852"),
    Country("Hungary", "+36"),
    Country("Iceland", "+354"),
    Country("India", "+91"),
    Country("Indonesia", "+62"),
    Country("Iran", "+98"),
    Country("Iraq", "+964"),
    Country("Ireland", "+353"),
    Country("Isle of Man", "+44-1624"),
    Country("Israel", "+972"),
    Country("Italy", "+39"),
    Country("Ivory Coast", "+225"),
    Country("Jamaica", "+1-876"),
    Country("Japan", "+81"),
    Country("Jersey", "+44-1534"),
    Country("Jordan", "+962"),
    Country("Kazakhstan", "+7"),
    Country("Kenya", "+254"),
    Country("Kiribati", "+686"),
    Country("Kosovo", "+383"),
    Country("Kuwait", "+965"),
    Country("Kyrgyzstan", "+996"),
    Country("Laos", "+856"),
    Country("Latvia", "+371"),
    Country("Lebanon", "+961"),
    Country("Lesotho", "+266"),
    Country("Liberia", "+231"),
    Country("Libya", "+218"),
    Country("Liechtenstein", "+423"),
    Country("Lithuania", "+370"),
    Country("Luxembourg", "+352"),
    Country("Macau", "+853"),
    Country("Macedonia", "+389"),
    Country("Madagascar", "+261"),
    Country("Malawi", "+265"),
    Country("Malaysia", "+60"),
    Country("Maldives", "+960"),
    Country("Mali", "+223"),
    Country("Malta", "+356"),
    Country("Marshall Islands", "+692"),
    Country("Mauritania", "+222"),
    Country("Mauritius", "+230"),
    Country("Mayotte", "+262"),
    Country("Mexico", "+52"),
    Country("Micronesia", "+691"),
    Country("Moldova", "+373"),
    Country("Monaco", "+377"),
    Country("Mongolia", "+976"),
    Country("Montenegro", "+382"),
    Country("Montserrat", "+1-664"),
    Country("Morocco", "+212"),
    Country("Mozambique", "+258"),
    Country("Myanmar", "+95"),
    Country("Namibia", "+264"),
    Country("Nauru", "+674"),
    Country("Nepal", "+977"),
    Country("Netherlands", "+31"),
    Country("Netherlands Antilles", "+599"),
    Country("New Caledonia", "+687"),
    Country("New Zealand", "+64"),
    Country("Nicaragua", "+505"),
    Country("Niger", "+227"),
    Country("Nigeria", "+234"),
    Country("Niue", "+683"),
    Country("North Korea", "+850"),
    Country("Northern Mariana Islands", "+1-670"),
    Country("Norway", "+47"),
    Country("Oman", "+968"),
    Country("Pakistan", "+92"),
    Country("Palau", "+680"),
    Country("Palestine", "+970"),
    Country("Panama", "+507"),
    Country("Papua New Guinea", "+675"),
    Country("Paraguay", "+595"),
    Country("Peru", "+51"),
    Country("Philippines", "+63"),
    Country("Pitcairn", "+64"),
    Country("Poland", "+48"),
    Country("Portugal", "+351"),
    Country("Puerto Rico", "+1-787, +1-939"),
    Country("Qatar", "+974"),
    Country("Republic of the Congo", "+242"),
    Country("Reunion", "+262"),
    Country("Romania", "+40"),
    Country("Russia", "+7"),
    Country("Rwanda", "+250"),
    Country("Saint Barthelemy", "+590"),
    Country("Saint Helena", "+290"),
    Country("Saint Kitts and Nevis", "+1-869"),
    Country("Saint Lucia", "+1-758"),
    Country("Saint Martin", "+590"),
    Country("Saint Pierre and Miquelon", "+508"),
    Country("Saint Vincent and the Grenadines", "+1-784"),
    Country("Samoa", "+685"),
    Country("San Marino", "+378"),
    Country("Sao Tome and Principe", "+239"),
    Country("Saudi Arabia", "+966"),
    Country("Senegal", "+221"),
    Country("Serbia", "+381"),
    Country("Seychelles", "+248"),
    Country("Sierra Leone", "+232"),
    Country("Singapore", "+65"),
    Country("Sint Maarten", "+1-721"),
    Country("Slovakia", "+421"),
    Country("Slovenia", "+386"),
    Country("Solomon Islands", "+677"),
    Country("Somalia", "+252"),
    Country("South Africa", "+27"),
    Country("South Korea", "+82"),
    Country("South Sudan", "+211"),
    Country("Spain", "+34"),
    Country("Sri Lanka", "+94"),
    Country("Sudan", "+249"),
    Country("Suriname", "+597"),
    Country("Svalbard and Jan Mayen", "+47"),
    Country("Swaziland", "+268"),
    Country("Sweden", "+46"),
    Country("Switzerland", "+41"),
    Country("Syria", "+963"),
    Country("Taiwan", "+886"),
    Country("Tajikistan", "+992"),
    Country("Tanzania", "+255"),
    Country("Thailand", "+66"),
    Country("Togo", "+228"),
    Country("Tokelau", "+690"),
    Country("Tonga", "+676"),
    Country("Trinidad and Tobago", "+1-868"),
    Country("Tunisia", "+216"),
    Country("Turkey", "+90"),
    Country("Turkmenistan", "+993"),
    Country("Turks and Caicos Islands", "+1-649"),
    Country("Tuvalu", "+688"),
    Country("U.S. Virgin Islands", "+1-340"),
    Country("Uganda", "+256"),
    Country("Ukraine", "+380"),
    Country("United Arab Emirates", "+971"),
    Country("United Kingdom", "+44"),
    Country("United States", "+1"),
    Country("Uruguay", "+598"),
    Country("Uzbekistan", "+998"),
    Country("Vanuatu", "+678"),
    Country("Vatican", "+379"),
    Country("Venezuela", "+58"),
    Country("Vietnam", "+84"),
    Country("Wallis and Futuna", "+681"),
    Country("Western Sahara", "+212"),
    Country("Yemen", "+967"),
    Country("Zambia", "+260"),
    Country("Zimbabwe", "+263")
)
val countryCodesToNames = mapOf(
    "+93" to "Afghanistan",
    "+355" to "Albania",
    "+213" to "Algeria",
    "+1-684" to "American Samoa",
    "+376" to "Andorra",
    "+244" to "Angola",
    "+1-264" to "Anguilla",
    "+672" to "Antarctica",
    "+1-268" to "Antigua and Barbuda",
    "+54" to "Argentina",
    "+374" to "Armenia",
    "+297" to "Aruba",
    "+61" to "Australia",
    "+43" to "Austria",
    "+994" to "Azerbaijan",
    "+1-242" to "Bahamas",
    "+973" to "Bahrain",
    "+880" to "Bangladesh",
    "+1-246" to "Barbados",
    "+375" to "Belarus",
    "+32" to "Belgium",
    "+501" to "Belize",
    "+229" to "Benin",
    "+1-441" to "Bermuda",
    "+975" to "Bhutan",
    "+591" to "Bolivia",
    "+387" to "Bosnia and Herzegovina",
    "+267" to "Botswana",
    "+55" to "Brazil",
    "+246" to "British Indian Ocean Territory",
    "+1-284" to "British Virgin Islands",
    "+673" to "Brunei",
    "+359" to "Bulgaria",
    "+226" to "Burkina Faso",
    "+257" to "Burundi",
    "+855" to "Cambodia",
    "+237" to "Cameroon",
    "+1" to "Canada",
    "+238" to "Cape Verde",
    "+1-345" to "Cayman Islands",
    "+236" to "Central African Republic",
    "+235" to "Chad",
    "+56" to "Chile",
    "+86" to "China",
    "+61" to "Christmas Island",
    "+61" to "Cocos Islands",
    "+57" to "Colombia",
    "+269" to "Comoros",
    "+682" to "Cook Islands",
    "+506" to "Costa Rica",
    "+385" to "Croatia",
    "+53" to "Cuba",
    "+599" to "Curacao",
    "+357" to "Cyprus",
    "+420" to "Czech Republic",
    "+243" to "Democratic Republic of the Congo",
    "+45" to "Denmark",
    "+253" to "Djibouti",
    "+1-767" to "Dominica",
    "+1-809, 1-829, 1-849" to "Dominican Republic",
    "+670" to "East Timor",
    "+593" to "Ecuador",
    "+20" to "Egypt",
    "+503" to "El Salvador",
    "+240" to "Equatorial Guinea",
    "+291" to "Eritrea",
    "+372" to "Estonia",
    "+251" to "Ethiopia",
    "+500" to "Falkland Islands",
    "+298" to "Faroe Islands",
    "+679" to "Fiji",
    "+358" to "Finland",
    "+33" to "France",
    "+689" to "French Polynesia",
    "+241" to "Gabon",
    "+220" to "Gambia",
    "+995" to "Georgia",
    "+49" to "Germany",
    "+233" to "Ghana",
    "+350" to "Gibraltar",
    "+30" to "Greece",
    "+299" to "Greenland",
    "+1-473" to "Grenada",
    "+1-671" to "Guam",
    "+502" to "Guatemala",
    "+44-1481" to "Guernsey",
    "+224" to "Guinea",
    "+245" to "Guinea-Bissau",
    "+592" to "Guyana",
    "+509" to "Haiti",
    "+504" to "Honduras",
    "+852" to "Hong Kong",
    "+36" to "Hungary",
    "+354" to "Iceland",
    "+91" to "India",
    "+62" to "Indonesia",
    "+98" to "Iran",
    "+964" to "Iraq",
    "+353" to "Ireland",
    "+44-1624" to "Isle of Man",
    "+972" to "Israel",
    "+39" to "Italy",
    "+225" to "Ivory Coast",
    "+1-876" to "Jamaica",
    "+81" to "Japan",
    "+44-1534" to "Jersey",
    "+962" to "Jordan",
    "+7" to "Kazakhstan",
    "+254" to "Kenya",
    "+686" to "Kiribati",
    "+383" to "Kosovo",
    "+965" to "Kuwait",
    "+996" to "Kyrgyzstan",
    "+856" to "Laos",
    "+371" to "Latvia",
    "+961" to "Lebanon",
    "+266" to "Lesotho",
    "+231" to "Liberia",
    "+218" to "Libya",
    "+423" to "Liechtenstein",
    "+370" to "Lithuania",
    "+352" to "Luxembourg",
    "+853" to "Macau",
    "+389" to "Macedonia",
    "+261" to "Madagascar",
    "+265" to "Malawi",
    "+60" to "Malaysia",
    "+960" to "Maldives",
    "+223" to "Mali",
    "+356" to "Malta",
    "+692" to "Marshall Islands",
    "+222" to "Mauritania",
    "+230" to "Mauritius",
    "+262" to "Mayotte",
    "+52" to "Mexico",
    "+691" to "Micronesia",
    "+373" to "Moldova",
    "+377" to "Monaco",
    "+976" to "Mongolia",
    "+382" to "Montenegro",
    "+1-664" to "Montserrat",
    "+212" to "Morocco",
    "+258" to "Mozambique",
    "+95" to "Myanmar",
    "+264" to "Namibia",
    "+674" to "Nauru",
    "+977" to "Nepal",
    "+31" to "Netherlands",
    "+599" to "Netherlands Antilles",
    "+687" to "New Caledonia",
    "+64" to "New Zealand",
    "+505" to "Nicaragua",
    "+227" to "Niger",
    "+234" to "Nigeria",
    "+683" to "Niue",
    "+850" to "North Korea",
    "+1-670" to "Northern Mariana Islands",
    "+47" to "Norway",
    "+968" to "Oman",
    "+92" to "Pakistan",
    "+680" to "Palau",
    "+970" to "Palestine",
    "+507" to "Panama",
    "+675" to "Papua New Guinea",
    "+595" to "Paraguay",
    "+51" to "Peru",
    "+63" to "Philippines",
    "+48" to "Poland",
    "+351" to "Portugal",
    "+1-787, +1-939" to "Puerto Rico",
    "+974" to "Qatar",
    "+242" to "Republic of the Congo",
    "+262" to "Reunion",
    "+40" to "Romania",
    "+7" to "Russia",
    "+250" to "Rwanda",
    "+590" to "Saint Barthelemy",
    "+290" to "Saint Helena",
    "+1-869" to "Saint Kitts and Nevis",
    "+1-758" to "Saint Lucia",
    "+590" to "Saint Martin",
    "+508" to "Saint Pierre and Miquelon",
    "+1-784" to "Saint Vincent and the Grenadines",
    "+685" to "Samoa",
    "+378" to "San Marino",
    "+239" to "Sao Tome and Principe",
    "+966" to "Saudi Arabia",
    "+221" to "Senegal",
    "+381" to "Serbia",
    "+248" to "Seychelles",
    "+232" to "Sierra Leone",
    "+65" to "Singapore",
    "+1-721" to "Sint Maarten",
    "+421" to "Slovakia",
    "+386" to "Slovenia",
    "+677" to "Solomon Islands",
    "+252" to "Somalia",
    "+27" to "South Africa",
    "+82" to "South Korea",
    "+211" to "South Sudan",
    "+34" to "Spain",
    "+94" to "Sri Lanka",
    "+249" to "Sudan",
    "+597" to "Suriname",
    "+47" to "Svalbard and Jan Mayen",
    "+268" to "Swaziland",
    "+46" to "Sweden",
    "+41" to "Switzerland",
    "+963" to "Syria",
    "+886" to "Taiwan",
    "+992" to "Tajikistan",
    "+255" to "Tanzania",
    "+66" to "Thailand",
    "+228" to "Togo",
    "+690" to "Tokelau",
    "+676" to "Tonga",
    "+1-868" to "Trinidad and Tobago",
    "+216" to "Tunisia",
    "+90" to "Turkey",
    "+993" to "Turkmenistan",
    "+1-649" to "Turks and Caicos Islands",
    "+688" to "Tuvalu",
    "+1-340" to "U.S. Virgin Islands",
    "+256" to "Uganda",
    "+380" to "Ukraine",
    "+971" to "United Arab Emirates",
    "+44" to "United Kingdom",
    "+1" to "United States",
    "+598" to "Uruguay",
    "+998" to "Uzbekistan",
    "+678" to "Vanuatu",
    "+379" to "Vatican",
    "+58" to "Venezuela",
    "+84" to "Vietnam",
    "+681" to "Wallis and Futuna",
    "+212" to "Western Sahara",
    "+967" to "Yemen",
    "+260" to "Zambia",
    "+263" to "Zimbabwe"
)
val countryCodeToFlagUrlMap = mapOf(
    "1" to "https://flagcdn.com/240x180/ca.png",  // Canada
    "7" to "https://flagcdn.com/240x180/ru.png",  // Russia
    "20" to "https://flagcdn.com/240x180/eg.png", // Egypt
    "27" to "https://flagcdn.com/240x180/za.png", // South Africa
    "30" to "https://flagcdn.com/240x180/gr.png", // Greece
    "31" to "https://flagcdn.com/240x180/nl.png", // Netherlands
    "32" to "https://flagcdn.com/240x180/be.png", // Belgium
    "33" to "https://flagcdn.com/240x180/fr.png", // France
    "34" to "https://flagcdn.com/240x180/es.png", // Spain
    "36" to "https://flagcdn.com/240x180/hu.png", // Hungary
    "39" to "https://flagcdn.com/240x180/it.png", // Italy
    "40" to "https://flagcdn.com/240x180/ro.png", // Romania
    "41" to "https://flagcdn.com/240x180/ch.png", // Switzerland
    "43" to "https://flagcdn.com/240x180/at.png", // Austria
    "44" to "https://flagcdn.com/240x180/gb.png", // United Kingdom
    "45" to "https://flagcdn.com/240x180/dk.png", // Denmark
    "46" to "https://flagcdn.com/240x180/se.png", // Sweden
    "47" to "https://flagcdn.com/240x180/no.png", // Norway
    "48" to "https://flagcdn.com/240x180/pl.png", // Poland
    "49" to "https://flagcdn.com/240x180/de.png", // Germany
    "51" to "https://flagcdn.com/240x180/pe.png", // Peru
    "52" to "https://flagcdn.com/240x180/mx.png", // Mexico
    "53" to "https://flagcdn.com/240x180/cu.png", // Cuba
    "54" to "https://flagcdn.com/240x180/ar.png", // Argentina
    "55" to "https://flagcdn.com/240x180/br.png", // Brazil
    "56" to "https://flagcdn.com/240x180/cl.png", // Chile
    "57" to "https://flagcdn.com/240x180/co.png", // Colombia
    "58" to "https://flagcdn.com/240x180/ve.png", // Venezuela
    "60" to "https://flagcdn.com/240x180/my.png", // Malaysia
    "61" to "https://flagcdn.com/240x180/au.png", // Australia
    "62" to "https://flagcdn.com/240x180/id.png", // Indonesia
    "63" to "https://flagcdn.com/240x180/ph.png", // Philippines
    "64" to "https://flagcdn.com/240x180/nz.png", // New Zealand
    "65" to "https://flagcdn.com/240x180/sg.png", // Singapore
    "66" to "https://flagcdn.com/240x180/th.png", // Thailand
    "81" to "https://flagcdn.com/240x180/jp.png", // Japan
    "82" to "https://flagcdn.com/240x180/kr.png", // South Korea
    "84" to "https://flagcdn.com/240x180/vn.png", // Vietnam
    "86" to "https://flagcdn.com/240x180/cn.png", // China
    "90" to "https://flagcdn.com/240x180/tr.png", // Turkey
    "91" to "https://flagcdn.com/240x180/in.png", // India
    "92" to "https://flagcdn.com/240x180/pk.png", // Pakistan
    "93" to "https://flagcdn.com/240x180/af.png", // Afghanistan
    "94" to "https://flagcdn.com/240x180/lk.png", // Sri Lanka
    "95" to "https://flagcdn.com/240x180/mm.png", // Myanmar
    "98" to "https://flagcdn.com/240x180/ir.png", // Iran
    "212" to "https://flagcdn.com/240x180/ma.png", // Morocco
    "213" to "https://flagcdn.com/240x180/dz.png", // Algeria
    "216" to "https://flagcdn.com/240x180/tn.png", // Tunisia
    "218" to "https://flagcdn.com/240x180/ly.png", // Libya
    "220" to "https://flagcdn.com/240x180/gm.png", // Gambia
    "221" to "https://flagcdn.com/240x180/sn.png", // Senegal
    "222" to "https://flagcdn.com/240x180/ma.png", // Mauritania
    "223" to "https://flagcdn.com/240x180/ml.png", // Mali
    "224" to "https://flagcdn.com/240x180/gn.png", // Guinea
    "225" to "https://flagcdn.com/240x180/ci.png", // Ivory Coast
    "226" to "https://flagcdn.com/240x180/bf.png", // Burkina Faso
    "227" to "https://flagcdn.com/240x180/ne.png", // Niger
    "228" to "https://flagcdn.com/240x180/tg.png", // Togo
    "229" to "https://flagcdn.com/240x180/bj.png", // Benin
    "230" to "https://flagcdn.com/240x180/mu.png", // Mauritius
    "231" to "https://flagcdn.com/240x180/lr.png", // Liberia
    "232" to "https://flagcdn.com/240x180/sl.png", // Sierra Leone
    "233" to "https://flagcdn.com/240x180/gh.png", // Ghana
    "234" to "https://flagcdn.com/240x180/ng.png", // Nigeria
    "235" to "https://flagcdn.com/240x180/td.png", // Chad
    "236" to "https://flagcdn.com/240x180/cf.png", // Central African Republic
    "237" to "https://flagcdn.com/240x180/cm.png", // Cameroon
    "238" to "https://flagcdn.com/240x180/cv.png", // Cape Verde
    "239" to "https://flagcdn.com/240x180/st.png", // Sao Tome and Principe
    "240" to "https://flagcdn.com/240x180/gq.png", // Equatorial Guinea
    "241" to "https://flagcdn.com/240x180/ga.png", // Gabon
    "242" to "https://flagcdn.com/240x180/cg.png", // Congo
    "243" to "https://flagcdn.com/240x180/cd.png", // Democratic Republic of the Congo
    "244" to "https://flagcdn.com/240x180/ao.png", // Angola
    "245" to "https://flagcdn.com/240x180/gw.png", // Guinea-Bissau
    "246" to "https://flagcdn.com/240x180/io.png", // British Indian Ocean Territory
    "247" to "https://flagcdn.com/240x180/sc.png", // Seychelles
    "248" to "https://flagcdn.com/240x180/sd.png", // Sudan
    "249" to "https://flagcdn.com/240x180/ru.png", // Russia
    "250" to "https://flagcdn.com/240x180/rw.png", // Rwanda
    "251" to "https://flagcdn.com/240x180/et.png", // Ethiopia
    "252" to "https://flagcdn.com/240x180/so.png", // Somalia
    "253" to "https://flagcdn.com/240x180/dj.png", // Djibouti
    "254" to "https://flagcdn.com/240x180/ke.png", // Kenya
    "255" to "https://flagcdn.com/240x180/tz.png", // Tanzania
    "256" to "https://flagcdn.com/240x180/ug.png", // Uganda
    "257" to "https://flagcdn.com/240x180/bi.png", // Burundi
    "258" to "https://flagcdn.com/240x180/mz.png", // Mozambique
    "260" to "https://flagcdn.com/240x180/zm.png", // Zambia
    "261" to "https://flagcdn.com/240x180/mg.png", // Madagascar
    "262" to "https://flagcdn.com/240x180/re.png", // Reunion
    "263" to "https://flagcdn.com/240x180/zw.png", // Zimbabwe
    "264" to "https://flagcdn.com/240x180/na.png", // Namibia
    "265" to "https://flagcdn.com/240x180/mw.png", // Malawi
    "266" to "https://flagcdn.com/240x180/ls.png", // Lesotho
    "267" to "https://flagcdn.com/240x180/bw.png", // Botswana
    "268" to "https://flagcdn.com/240x180/sz.png", // Swaziland
    "269" to "https://flagcdn.com/240x180/km.png", // Comoros
    "290" to "https://flagcdn.com/240x180/sh.png", // Saint Helena
    "291" to "https://flagcdn.com/240x180/er.png", // Eritrea
    "297" to "https://flagcdn.com/240x180/aw.png", // Aruba
    "298" to "https://flagcdn.com/240x180/fo.png", // Faroe Islands
    "299" to "https://flagcdn.com/240x180/gl.png", // Greenland
    "350" to "https://flagcdn.com/240x180/gi.png", // Gibraltar
    "351" to "https://flagcdn.com/240x180/pt.png", // Portugal
    "352" to "https://flagcdn.com/240x180/lu.png", // Luxembourg
    "353" to "https://flagcdn.com/240x180/ie.png", // Ireland
    "354" to "https://flagcdn.com/240x180/is.png", // Iceland
    "355" to "https://flagcdn.com/240x180/al.png", // Albania
    "356" to "https://flagcdn.com/240x180/mt.png", // Malta
    "357" to "https://flagcdn.com/240x180/cy.png", // Cyprus
    "358" to "https://flagcdn.com/240x180/fi.png", // Finland
    "359" to "https://flagcdn.com/240x180/bg.png", // Bulgaria
    "370" to "https://flagcdn.com/240x180/lt.png", // Lithuania
    "371" to "https://flagcdn.com/240x180/lv.png", // Latvia
    "372" to "https://flagcdn.com/240x180/ee.png", // Estonia
    "373" to "https://flagcdn.com/240x180/md.png", // Moldova
    "374" to "https://flagcdn.com/240x180/am.png", // Armenia
    "375" to "https://flagcdn.com/240x180/by.png", // Belarus
    "376" to "https://flagcdn.com/240x180/ad.png", // Andorra
    "377" to "https://flagcdn.com/240x180/mc.png", // Monaco
    "378" to "https://flagcdn.com/240x180/sm.png", // San Marino
    "379" to "https://flagcdn.com/240x180/va.png", // Vatican City
    "380" to "https://flagcdn.com/240x180/ua.png", // Ukraine
    "381" to "https://flagcdn.com/240x180/rs.png", // Serbia
    "382" to "https://flagcdn.com/240x180/me.png", // Montenegro
    "385" to "https://flagcdn.com/240x180/hr.png", // Croatia
    "386" to "https://flagcdn.com/240x180/si.png", // Slovenia
    "387" to "https://flagcdn.com/240x180/ba.png", // Bosnia and Herzegovina
    "389" to "https://flagcdn.com/240x180/mk.png", // North Macedonia
    "420" to "https://flagcdn.com/240x180/cz.png", // Czechia
    "421" to "https://flagcdn.com/240x180/sk.png", // Slovakia
    "423" to "https://flagcdn.com/240x180/li.png", // Liechtenstein
    "500" to "https://flagcdn.com/240x180/fk.png", // Falkland Islands
    "501" to "https://flagcdn.com/240x180/bz.png", // Belize
    "502" to "https://flagcdn.com/240x180/gt.png", // Guatemala
    "503" to "https://flagcdn.com/240x180/sv.png", // El Salvador
    "504" to "https://flagcdn.com/240x180/hn.png", // Honduras
    "505" to "https://flagcdn.com/240x180/ni.png", // Nicaragua
    "506" to "https://flagcdn.com/240x180/cr.png", // Costa Rica
    "507" to "https://flagcdn.com/240x180/pa.png", // Panama
    "508" to "https://flagcdn.com/240x180/pm.png", // Saint Pierre and Miquelon
    "509" to "https://flagcdn.com/240x180/ht.png", // Haiti
    "590" to "https://flagcdn.com/240x180/gp.png", // Guadeloupe
    "591" to "https://flagcdn.com/240x180/bo.png", // Bolivia
    "592" to "https://flagcdn.com/240x180/gy.png", // Guyana
    "593" to "https://flagcdn.com/240x180/ec.png", // Ecuador
    "594" to "https://flagcdn.com/240x180/fr.png", // French Guiana
    "595" to "https://flagcdn.com/240x180/py.png", // Paraguay
    "596" to "https://flagcdn.com/240x180/mq.png", // Martinique
    "597" to "https://flagcdn.com/240x180/sr.png", // Suriname
    "598" to "https://flagcdn.com/240x180/uy.png", // Uruguay
    "599" to "https://flagcdn.com/240x180/an.png", // Netherlands Antilles
    "670" to "https://flagcdn.com/240x180/tl.png", // Timor-Leste
    "672" to "https://flagcdn.com/240x180/au.png", // Australia
    "673" to "https://flagcdn.com/240x180/bn.png", // Brunei
    "674" to "https://flagcdn.com/240x180/nr.png", // Nauru
    "675" to "https://flagcdn.com/240x180/pg.png", // Papua New Guinea
    "676" to "https://flagcdn.com/240x180/to.png", // Tonga
    "677" to "https://flagcdn.com/240x180/sb.png", // Solomon Islands
    "678" to "https://flagcdn.com/240x180/vu.png", // Vanuatu
    "679" to "https://flagcdn.com/240x180/fj.png", // Fiji
    "680" to "https://flagcdn.com/240x180/pw.png", // Palau
    "681" to "https://flagcdn.com/240x180/wf.png", // Wallis and Futuna
    "682" to "https://flagcdn.com/240x180/ck.png", // Cook Islands
    "683" to "https://flagcdn.com/240x180/nu.png", // Niue
    "685" to "https://flagcdn.com/240x180/ws.png", // Samoa
    "686" to "https://flagcdn.com/240x180/ki.png", // Kiribati
    "687" to "https://flagcdn.com/240x180/nc.png", // New Caledonia
    "688" to "https://flagcdn.com/240x180/tv.png", // Tuvalu
    "689" to "https://flagcdn.com/240x180/pf.png", // French Polynesia
    "690" to "https://flagcdn.com/240x180/tk.png", // Tokelau
    "691" to "https://flagcdn.com/240x180/fm.png", // Micronesia
    "692" to "https://flagcdn.com/240x180/mh.png", // Marshall Islands
    "850" to "https://flagcdn.com/240x180/kp.png", // North Korea
    "852" to "https://flagcdn.com/240x180/hk.png", // Hong Kong
    "853" to "https://flagcdn.com/240x180/mo.png", // Macau
    "855" to "https://flagcdn.com/240x180/kh.png", // Cambodia
    "856" to "https://flagcdn.com/240x180/la.png", // Laos
    "880" to "https://flagcdn.com/240x180/bd.png", // Bangladesh
    "886" to "https://flagcdn.com/240x180/tw.png", // Taiwan
    "960" to "https://flagcdn.com/240x180/mv.png", // Maldives
    "961" to "https://flagcdn.com/240x180/lb.png", // Lebanon
    "962" to "https://flagcdn.com/240x180/jo.png", // Jordan
    "963" to "https://flagcdn.com/240x180/sy.png", // Syria
    "964" to "https://flagcdn.com/240x180/iq.png", // Iraq
    "965" to "https://flagcdn.com/240x180/kw.png", // Kuwait
    "966" to "https://flagcdn.com/240x180/sa.png", // Saudi Arabia
    "967" to "https://flagcdn.com/240x180/ye.png", // Yemen
    "968" to "https://flagcdn.com/240x180/om.png", // Oman
    "970" to "https://flagcdn.com/240x180/ps.png", // Palestine, State of
    "971" to "https://flagcdn.com/240x180/ae.png", // United Arab Emirates
    "972" to "https://flagcdn.com/240x180/il.png", // Israel
    "973" to "https://flagcdn.com/240x180/bh.png", // Bahrain
    "974" to "https://flagcdn.com/240x180/qa.png", // Qatar
    "975" to "https://flagcdn.com/240x180/bt.png", // Bhutan
    "976" to "https://flagcdn.com/240x180/mn.png", // Mongolia
    "977" to "https://flagcdn.com/240x180/np.png", // Nepal
    "979" to "https://flagcdn.com/240x180/xx.png", // International Premium Rate Service (IPRS)
    "991" to "https://flagcdn.com/240x180/xx.png", // Trial of a proposed new international telecommunication public correspondence service, to certain destinations in the NMT (Nordic Mobile Telephone) system
    "992" to "https://flagcdn.com/240x180/tj.png", // Tajikistan
    "993" to "https://flagcdn.com/240x180/tm.png", // Turkmenistan
    "994" to "https://flagcdn.com/240x180/az.png", // Azerbaijan
    "995" to "https://flagcdn.com/240x180/ge.png", // Georgia
    "996" to "https://flagcdn.com/240x180/kg.png", // Kyrgyzstan
    "997" to "https://flagcdn.com/240x180/xx.png", // reserved for future global service
    "998" to "https://flagcdn.com/240x180/uz.png", // Uzbekistan
    "999" to "https://flagcdn.com/240x180/xx.png", // reserved for future global service
    "1-242" to "https://flagcdn.com/240x180/bs.png", // Bahamas
    "1-246" to "https://flagcdn.com/240x180/bb.png", // Barbados
    "1-264" to "https://flagcdn.com/240x180/ai.png", // Anguilla
    "1-268" to "https://flagcdn.com/240x180/ag.png", // Antigua and Barbuda
    "1-284" to "https://flagcdn.com/240x180/vg.png", // British  Islands
    "1-340" to "https://flagcdn.com/240x180/vi.png", // U.S. Virgin Islands
    "1-345" to "https://flagcdn.com/240x180/ky.png", // Cayman Islands
    "1-441" to "https://flagcdn.com/240x180/bm.png", // Bermuda
    "1-473" to "https://flagcdn.com/240x180/gd.png", // Grenada
    "1-649" to "https://flagcdn.com/240x180/tc.png", // Turks and Caicos Islands
    "1-664" to "https://flagcdn.com/240x180/ms.png", // Montserrat
    "1-670" to "https://flagcdn.com/240x180/mp.png", // Northern Mariana Islands
    "1-671" to "https://flagcdn.com/240x180/gu.png", // Guam
    "1-684" to "https://flagcdn.com/240x180/as.png", // American Samoa
    "1-758" to "https://flagcdn.com/240x180/lc.png", // Saint Lucia
    "1-767" to "https://flagcdn.com/240x180/dm.png", // Dominica
    "1-784" to "https://flagcdn.com/240x180/vc.png", // Saint Vincent and the Grenadines
    "1-787" to "https://flagcdn.com/240x180/pr.png", // Puerto Rico
    "1-809" to "https://flagcdn.com/240x180/do.png", // Dominican Republic
    "1-868" to "https://flagcdn.com/240x180/tt.png", // Trinidad and Tobago
    "1-869" to "https://flagcdn.com/240x180/kn.png", // Saint Kitts and Nevis
    "1-876" to "https://flagcdn.com/240x180/jm.png", // Jamaica
    "211" to "https://flagcdn.com/240x180/ss.png", // South Sudan
    "5-999" to "https://flagcdn.com/240x180/sx.png", // Sint Maarten (Dutch part)
    "1-721" to "https://flagcdn.com/240x180/sx.png", // Sint Maarten
)
