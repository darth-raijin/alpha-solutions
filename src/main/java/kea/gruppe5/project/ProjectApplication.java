package kea.gruppe5.project;
//MM
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kea.gruppe5.project.repository.ProjectRepository;
import kea.gruppe5.project.repository.SubprojectRepository;
import kea.gruppe5.project.repository.SubtaskRepository;
import kea.gruppe5.project.repository.TaskRepository;
import kea.gruppe5.project.repository.UserRepository;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		// Connecting to DB and loading existing entities into repository
		UserRepository.loadUsers();
		ProjectRepository.loadProjects();
		SubprojectRepository.loadSubprojects();
		TaskRepository.loadTasks();
		SubtaskRepository.loadSubtasks();
	}

}
