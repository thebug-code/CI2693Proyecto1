package ve.usb.libGrafo

public class VerticeBFS(
    var v: Int, 
    var d: Int = Int.MAX_VALUE, 
    var color: Color = Color.BLANCO, 
    var pred: VerticeBFS? = null
)
