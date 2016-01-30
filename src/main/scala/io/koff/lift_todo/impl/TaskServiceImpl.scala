package io.koff.lift_todo.impl

import java.util.UUID

import io.koff.lift_todo.{TaskInfo, TaskService}

import scala.collection.mutable

/**
 * The main implementation of TaskService interface
 */
class TaskServiceImpl extends TaskService{
  //use a hash map as a storage for tasks and synchronize access to it
  private val taskStorage = mutable.LinkedHashMap[UUID, TaskInfo]()

  override def create(taskInfo: TaskInfo): TaskInfo = synchronized {
    val newId = UUID.randomUUID()
    val toSave = taskInfo.copy(id = Some(newId.toString))
    
    taskStorage.get(newId) match {
      case Some(_) =>
        //just in case if somehow new generated id equals to already existing key
        throw new IllegalStateException(s"key is duplicated: [$newId]")
      case None =>
        taskStorage.put(newId, toSave)
        toSave
    }
  }

  override def update(id: UUID, taskInfo: TaskInfo): TaskInfo = synchronized {
    taskStorage.get(id) match {
      case Some(oldTask) => 
        val toSave = oldTask.copy(description = taskInfo.description, isReady = taskInfo.isReady)
        taskStorage.put(id, toSave)
        toSave
      case None =>
        throw new IllegalStateException(s"can't find a task with ID: [$id]")
    }
  }

  override def getAllTasks: Seq[TaskInfo] = synchronized {
    taskStorage.values.toSeq
  }

  /**
   * Removes an existing task
   * @param id id of a task to remove
   */
  override def removeTask(id: UUID): Unit = synchronized {
    taskStorage.remove(id)
  }
}
