import {defineStore} from 'pinia';
import axios from 'axios';
import type {Book} from "@/model/Book";

export const useBooksStore = defineStore({
    id: 'books',

    state: () => ({
        books: [] as Book[],
    }),
    actions: {
        async getBooks() {
            try {
                const response = await axios.get('http://localhost:8080/api/books');
                this.books = response.data;
            } catch (error) {
                console.error(error);
            }
        },

        async addBook(newBook) {
            try {
                const response = await axios.post('http://localhost:8080/api/books', newBook);
                this.books.push(response.data);
            } catch (error) {
                console.error(error);
            }
        },

        async updateBook(bookId:number, request) {
            try {
                await axios.put(`http://localhost:8080/api/books/${bookId}`, request);
                await this.getBooks();
            } catch (error) {
                console.error(error);
            }
        },

        async deleteBook(bookId:number) {
            try {
                await axios.delete(`http://localhost:8080/api/books/${bookId}`);
                this.books = this.books.filter(book => book.id !== bookId);
            } catch (error) {
                console.error(error);
            }
        }
    },
});
