# Alpha-Solutions

Project name is a tool that allows project managers to structure and plan the execution of any given project.

Possible to register as a user, and create projects. It is possible to break down the project into sub-elements, to understand the full scope of what needs to be compleeted.
It is also possible to calculate a time estimate.
## Prerequisites

Before you begin, ensure you have met the following requirements:
* You have installed version 11 of Java 
* Installed MySQL 8.0.25


## Installing project

To install Alpha-Solutions, follow these steps:

Linux and macOS:
```
1: Clone the repository - git clone https://github.com/macow-lab/alpha-solutions.git
2: Open the folder in your IDE of choice
3: Run the Spring project
4: Visit localhost:8080
```

## Hosting on Heroku
```
1: Fork the repository
2: Create new App on Heroku
3: Open the project and navigate to 'Deploy'
4: Connect with your github
5: Find the newly forked repository
6: Add ClearDB as an extension
7: Navigate to Settings for the app
8: Reveal the config variables
   The structure for the provided link: 
   mysql://username:password@eu-cdbr-west-01.cleardb.com/db_name?reconnect=true
   URL for the application.properties:
   jdbc:mysql://eu-cdbr-west-01.cleardb.com/db_name?reconnect=true&autoReconnect=true
```
## Using project

To use Alpha-Solutions, follow these steps:

1: Register as a user
2: Create project
3: Create sub elements

Add run commands and examples you think users will find useful. Provide an options reference for bonus points!

## Contributing to <project_name>
<!--- If your README is long or you have some specific process or steps you want contributors to follow, consider creating a separate CONTRIBUTING.md file--->
To contribute to Alpha-Solutions, follow these steps:

1. Fork this repository.
2. Create a branch: `git checkout -b <branch_name>`.
3. Make your changes and commit them: `git commit -m '<commit_message>'`
4. Push to the original branch: `git push origin <project_name>/<location>`
5. Create the pull request.

Alternatively see the GitHub documentation on [creating a pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).

## Contributors

Thanks to the following people who have contributed to this project:

* [@macow-lab](https://github.com/macow-lab) 😈
* [@JensLundJoergensen](https://github.com/JensLundJoergensen)
* [@calchuchesta](https://github.com/calchuchesta) 🐛
