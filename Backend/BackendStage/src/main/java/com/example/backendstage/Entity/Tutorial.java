package com.example.backendstage.Entity;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

    @Document
    public class Tutorial {
        @Id
        private String id;
        @Field
        private String titre;
        @Field
        private String description;
        @Field
        private String imagePath;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public Tutorial(String id, String titre, String description, String imagePath) {
            this.id = id;
            this.titre = titre;
            this.description = description;
            this.imagePath = imagePath;
        }

        public Tutorial() {
        }

        public Tutorial(String titre, String description) {
            this.titre = titre;
            this.description = description;
        }

        @Override
        public String toString() {
            return "Tutorial{" +
                    "id='" + id + '\'' +
                    ", titre='" + titre + '\'' +
                    ", description='" + description + '\'' +
                    ", imagePath='" + imagePath + '\'' +
                    '}';
        }
    }


