package ca.mcit.bigdata.course2.project.ReadWrite

import ca.mcit.bigdata.course2.project.model.{Calender, Route, Trip}

import scala.io.Source

class DataReader {

  val filePathRoutes = "/Users/ishrathnayeem/MY MAC/Study/Big Data/MCIT/Required Files/stm-data/routes.txt"
  val filePathCalender = "/Users/ishrathnayeem/MY MAC/Study/Big Data/MCIT/Required Files/stm-data/calendar.txt"
  val filePathTrips = "/Users/ishrathnayeem/MY MAC/Study/Big Data/MCIT/Required Files/stm-data/trips.txt"
  def getRouteList:List[Route] = {
    Source.fromFile(filePathRoutes).getLines().drop(1)
      .map(line => line.split(","))
      .map(a => Route(a(0).toInt, a(1), a(2), a(3), a(4), a(5), a(6))).toList
  }

  def getCalenderList: List[Calender] = {
    Source.fromFile(filePathCalender).getLines().drop(1)
      .map(line => line.split(","))
      .map(a => Calender(a(0), a(1), a(2), a(3), a(4), a(5), a(6), a(7), a(8), a(9))).toList
  }

  def getTripList: List[Trip] = {
    Source.fromFile(filePathTrips).getLines().drop(1)
      .map(line => line.split(","))
      .map(a => Trip(a(0).toInt, a(1), a(2), a(3), a(4), a(5), a(6))).toList
  }
}