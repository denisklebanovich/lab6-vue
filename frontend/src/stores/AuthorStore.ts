import {defineStore} from 'pinia';
import axios from 'axios';
import type {Author} from "@/model/Author";

export const useAuthorsStore = defineStore({
    id: 'authors',

    state: () => ({
        authors: [] as Author[],
    }),
    actions: {
        async getAuthors() {
            try {
                const response = await axios.get('http://localhost:8080/api/authors');
                this.authors = response.data;
            } catch (error) {
                console.error(error);
            }
        },

        async addAuthor(newAuthor) {
            try {
                const response = await axios.post('http://localhost:8080/api/authors', newAuthor);
                this.authors.push(response.data);
            } catch (error) {
                console.error(error);
            }
        },

        async updateAuthor(authorId:number, request) {
            try {
                await axios.put(`http://localhost:8080/api/authors/${authorId}`, request);
                await this.getAuthors();
            } catch (error) {
                console.error(error);
            }
        },

        async deleteAuthor(authorId:number) {
            try {
                await axios.delete(`http://localhost:8080/api/authors/${authorId}`);
                this.authors = this.authors.filter(book => book.id !== authorId);
            } catch (error) {
                console.error(error);
            }
        }
    },
});
