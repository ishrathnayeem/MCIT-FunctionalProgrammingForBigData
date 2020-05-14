package DataLookup

import Models.Route
class RouteLookup(routes: List[Route]) {
  private val lookupTable: Map[Int, Route] =  routes.map(route => route.route_id.getOrElse(0) -> route).toMap
  def lookup(routeId: Int): Route = lookupTable.getOrElse(routeId, null)
}