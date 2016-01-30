package io.koff.lift_todo

import java.util.UUID

import io.koff.lift_todo.impl.TaskServiceImpl
import net.liftweb.common.Full
import net.liftweb.http.rest.RestHelper
import net.liftweb.http._
import net.liftweb.json._

/**
 * Rest API for tasks
 */
object TaskRestAPI extends RestHelper {
  private val taskService: TaskService = new TaskServiceImpl

  serve {
    //return all tasks
    case Get("api" :: "tasks" :: Nil, _) =>
      val tasks = taskService.getAllTasks
      Full(Extraction.decompose(tasks))

    //create a new task
    case JsonPost("api" :: "tasks" :: Nil, json -> _) =>
      val taskInfo = json.extract[TaskInfo]
      val savedTask = taskService.create(taskInfo)
      Full(Extraction.decompose(savedTask))

    //update an existing task
    case JsonPut("api" :: "tasks" :: taskIdStr :: Nil, json -> _) =>
      val taskInfo = json.extract[TaskInfo]
      val taskId = UUID.fromString(taskIdStr)
      val updated = taskService.update(taskId, taskInfo)
      Full(Extraction.decompose(updated))

    //remove an existing task
    case Delete("api" :: "tasks" :: taskIdStr :: Nil, _) =>
      val taskId = UUID.fromString(taskIdStr)
      taskService.removeTask(taskId)
      OkResponse()

    case Put("api" :: "tasks" :: taskId :: "ready" :: Nil, _) =>
      Full(Extraction.decompose(s"task#$taskId marked as ready"))
    case Put("api" :: "tasks" :: taskId :: "unfinished" :: Nil, _) =>
      Full(Extraction.decompose(s"task#$taskId marked as unfinished"))
  }
}
