package ca.mcit.bigdata.course2.project.model

case class Trip(route_id: Int,
                service_id:String,
                trip_id:String,
                trip_headsign:String,
                direction_id:String,
                 shape_id:String,
                wheelchair_accessible:String,
                note_fr:String="",
                note_en:String ="")
