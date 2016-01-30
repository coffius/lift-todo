package io.koff.lift_todo

import java.util.UUID

/**
 * Information about a task
 */
case class TaskInfo(id: Option[String] = None, description: String, isReady: Boolean = false)
