package io.koff.lift_todo

import java.util.UUID

/**
 * The service for working with tasks
 */
trait TaskService {
  /**
   * Creates a new task
   * @param taskInfo information about task
   * @return created task
   */
  def create(taskInfo: TaskInfo): TaskInfo

  /**
   * Updates an existing task
   * @param id id of a task to update
   * @param taskInfo information about task
   * @return updated task
   */
  def update(id: UUID, taskInfo: TaskInfo): TaskInfo

  /**
   * Returns all tasks
   * @return seq of existing tasks
   */
  def getAllTasks: Seq[TaskInfo]

  /**
   * Removes an existing task
   * @param taskId id of a task to remove
   */
  def removeTask(taskId: UUID): Unit
}
