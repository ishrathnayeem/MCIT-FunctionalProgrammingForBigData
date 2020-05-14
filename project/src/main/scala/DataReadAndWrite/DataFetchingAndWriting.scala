package DataReadAndWrite
import java.io.{BufferedWriter, FileWriter}
import Models.TripandCalender
import au.com.bytecode.opencsv.CSVWriter
import scala.jdk.CollectionConverters._
import scala.collection.mutable.ListBuffer

class DataFetchingAndWriting(enrichedList: List[TripandCalender]) {
  val outputFile = new BufferedWriter(new FileWriter("/Users/ishrathnayeem/MY MAC/Study/Big Data/ProjectOutput.csv"))
  val csvWriter = new CSVWriter(outputFile)
  val csvHeaderFields: Array[String] = Array("Route Id", "Service Id", "Trip Id", "Trip Head Sign", "Direction Id", "Shape Id",
    "Wheelchair accessible", "Note_FR", "Note En", "Agency Id", "Route Short Name", "Route Long Name", "Route Type", "Route Url",
    "Route Colour", "Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Saturday", "Sunday", "Start Date", "End Date")
  var listOfRecords = new ListBuffer[Array[String]]()
  listOfRecords += csvHeaderFields
  for{element <- enrichedList} yield listOfRecords += Array(
    element.tripandRoute.routes.route_id.getOrElse().toString, element.calender.service_id.getOrElse().toString, element.tripandRoute.trips.trip_id.getOrElse().toString,
    element.tripandRoute.trips.trip_headsign.getOrElse().toString, element.tripandRoute.trips.direction_id.getOrElse().toString, element.tripandRoute.trips.shape_id.getOrElse().toString,
    element.tripandRoute.trips.wheelchair_accessible.getOrElse().toString, element.tripandRoute.trips.note_fr.getOrElse().toString, element.tripandRoute.trips.note_en.getOrElse().toString,
    element.tripandRoute.routes.agency_id.getOrElse().toString, element.tripandRoute.routes.route_short_name.getOrElse().toString, element.tripandRoute.routes.route_long_name.getOrElse().toString,
    element.tripandRoute.routes.route_type.getOrElse().toString, element.tripandRoute.routes.route_url.getOrElse().toString, element.tripandRoute.routes.route_color.getOrElse().toString,
    element.calender.monday.getOrElse().toString, element.calender.tuesday.getOrElse().toString, element.calender.wednesday.getOrElse().toString, element.calender.thursday.getOrElse().toString,
    element.calender.friday.getOrElse().toString, element.calender.saturday.getOrElse().toString, element.calender.sunday.getOrElse().toString,
    element.calender.start_date.getOrElse().toString, element.calender.end_date.getOrElse().toString)
  csvWriter.writeAll(listOfRecords.asJava)
  outputFile.close()
}