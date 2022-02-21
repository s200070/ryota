package jp.ac.it_college.std.s20007.getupryota


data class alarm(
    val id: Long,
    val time:String,
    val name:String,
    val sound:String,
    val week: String,
    val repeat:Boolean,
    val format: Int,
)