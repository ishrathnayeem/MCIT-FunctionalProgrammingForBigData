package ca.mcit.bigdata.course2.project

import ca.mcit.bigdata.course2.project.ReadWrite.{DataReader, DataWriter}
import ca.mcit.bigdata.course2.project.intermediate.{EnrichTrip, TripRoute}
import ca.mcit.bigdata.course2.project.model.{Calender, Route, Trip}
import ca.mcit.bigdata.course2.project.lookup._

object Main extends App{

  val readData : DataReader = new DataReader

  val tripList: List[Trip] = readData.getTripList
  val routeList: List[Route] = readData.getRouteList
  val calanderList: List[Calender] = readData.getCalenderList

  val routeLookup = new RouteLookup(routeList)
  val calenderLookUp = new CalendarLookup(calanderList)

  val enrichedTripRoute: List[TripRoute] = tripList.map(trip => TripRoute(trip,
    routeLookup.lookup(trip.route_id))).toList

  val enrichedTrip: List[EnrichTrip] = enrichedTripRoute.map(tripRoute => EnrichTrip(tripRoute,
    calenderLookUp.lookup(tripRoute.trips.service_id))).toList



  val writer = new DataWriter(enrichedTrip)
  writer.writeData
}
