package DataLookup

import Models.Calender
class CalenderLookup(calendars: List[Calender]) {
  private val lookupTable: Map[String, Calender] = calendars.map(calendar => calendar.service_id.getOrElse("") -> calendar).toMap
  def lookup(serviceId: String): Calender = lookupTable.getOrElse(serviceId, null)
}
