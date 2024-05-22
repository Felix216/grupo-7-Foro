<template>
    <div class="relative inline-block text-left">
      <div>
        <button @click="toggleDropdown" type="button" class="inline-flex justify-center w-full rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
            <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
            </svg>
        </button>
      </div>
        <div v-if="isOpen" class="origin-top-right absolute right-0 mt-2 w-56 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 divide-y divide-gray-100 focus:outline-none">
            <div class="py-1">
                <button @click="mostrarVentanaEditar" class="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-100 w-full">Editar</button>
                <button @click="borrarPost" class="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-100 w-full">Eliminar Publicacion</button>
            </div>
        </div>
        <div v-if="editIsVisible">
            <EditPost @close="mostrarVentanaEditar()"/>
        </div>
    </div>
  </template>
  
<script>
    import axios from 'axios';
    import EditPost from './EditPost.vue';
    export default {
        props: ['postID'],
        components: {
            EditPost,
        },
        data() {
            return {
                isOpen: false,
                editIsVisible: false,
                ID: this.postID
            }
        },
        methods: {
            async borrarPost() {
                try {
                    let result = await axios.delete(`http://localhost:3000/posts/${this.ID}`);

                    if (result.status == 200) {
                        alert("Publicacion eliminada con exito.")
                    }
                } catch (error) {
                    console.error(error);
                }
            },
            toggleDropdown() {
                this.isOpen = !this.isOpen;
            },
            mostrarVentanaEditar() {
                this.editIsVisible = !this.editIsVisible;
            },
        }
    }
</script>
