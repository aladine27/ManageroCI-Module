export class Project {
    id: string;
    name: string;
    description: string;
    gitUrl: string;
  
    constructor(id: string, name: string, description: string, gitUrl: string) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.gitUrl = gitUrl;
    }
  }
  