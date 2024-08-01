export class Project {
    id: string;
    name: string;
    description: string;
    gitUrl: string;
    token: string;

  
    constructor(id: string, name: string, description: string, gitUrl: string, token: string) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.gitUrl = gitUrl;
      this.token = token;
    }
  }
  