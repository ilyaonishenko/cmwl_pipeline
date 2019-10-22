package cromwell.pipeline.datastorage.dao.repository

import cromwell.pipeline.database.PipelineDatabaseEngine
import cromwell.pipeline.datastorage.dao.entry.ProjectEntry
import cromwell.pipeline.datastorage.dto.{Project, ProjectId}

import scala.concurrent.Future

class ProjectRepository(pipelineDatabaseEngine: PipelineDatabaseEngine, projectEntry: ProjectEntry) {

  import pipelineDatabaseEngine.profile.api._

  private val database = pipelineDatabaseEngine.database

  def getProjectById(projectId: ProjectId): Future[Option[Project]] = database.run(projectEntry.getProjectByIdAction(projectId).result.headOption)

  def addUser(project: Project): Future[ProjectId] = database.run(projectEntry.addProjectAction(project))
}
