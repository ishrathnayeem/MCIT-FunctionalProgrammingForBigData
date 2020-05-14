import DataLookup._
import DataReadAndWrite.DataFetchingAndWriting
import Models._
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Main extends App{
  val tripList= Source.fromFile("/Users/ishrathnayeem/MY MAC/Study/Big Data/MCIT/Required Files/stm-data/trips.txt").getLines().drop(1)
    .map(line => line.split(","))
    .map(a => Trip(Some(a(0).toInt), Some(a(1)), Some(a(2)), Some(a(3)), Some(a(4)), Some(a(5)), Some(a(6)))).toList
  val routeList = Source.fromFile("/Users/ishrathnayeem/MY MAC/Study/Big Data/MCIT/Required Files/stm-data/routes.txt").getLines().drop(1)
    .map(line => line.split(","))
    .map(a => Route(Some(a(0).toInt), Some(a(1)), Some(a(2)), Some(a(3)), Some(a(4)), Some(a(5)), Some(a(6)))).toList
  val calenderList=  Source.fromFile("/Users/ishrathnayeem/MY MAC/Study/Big Data/MCIT/Required Files/stm-data/calendar.txt").getLines().drop(1)
    .map(line => line.split(","))
    .map(a => Calender(Some(a(0)), Some(a(1)), Some(a(2)), Some(a(3)), Some(a(4)), Some(a(5)), Some(a(6)), Some(a(7)), Some(a(8)), Some(a(9)))).toList
  val routeLookup = new RouteLookup(routeList)
  val calenderLookUp = new CalenderLookup(calenderList)
  val enrichedTripRouteResult:List[TripandRoute] = tripList.map(trip => TripandRoute(trip,
    routeLookup.lookup(trip.route_id.getOrElse(0))))
  var enrichedTripResult = new ListBuffer[TripandCalender]()
  for{
    tripandRoute <- enrichedTripRouteResult
  } yield enrichedTripResult += TripandCalender(tripandRoute, calenderLookUp.lookup(tripandRoute.trips.service_id.getOrElse("")))
  val writer = new DataFetchingAndWriting(enrichedTripResult.toList.filter(p => p.calender.monday.getOrElse().toString=="1" && p.tripandRoute.routes.route_url.getOrElse().toString.contains("metro")))
}
