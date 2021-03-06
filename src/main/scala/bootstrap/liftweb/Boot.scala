package bootstrap.liftweb

import io.koff.lift_todo.TaskRestAPI
import net.liftweb.http.{Html5Properties, LiftRules, Req}
import net.liftweb.sitemap.{Menu, SiteMap}

class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("io.koff")

    // Build SiteMap
    def sitemap(): SiteMap = SiteMap(
      Menu.i("Home") / "index"
    )

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    LiftRules.dispatch.append(TaskRestAPI)
  }
}