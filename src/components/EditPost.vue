<template>
    <div @click="cerrar()" class="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50">
        <div class="bg-white rounded shadow p-8 w-1/4" @click.stop>
            <button @click="cerrar()" class="text-gray-500 transition hover:text-gray-600 absolute ml-72">
                <svg class="h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"/>
                </svg>
            </button>
            <div class="mt-8">
                <h1 class="mb-4 font-bold">Editar Publicacion</h1>
                <div class="flex flex-col">
                    <label class="">Titulo</label>
                    <input class="px-4 py-2 bg-slate-100 rounded" type="text" v-model="tittle">
                </div>
                <select v-model="category" class="flex flex-row mt-4 mb-2">
                    <option disabled value="">Por favor selecciona una categoría</option>
                    <option v-for="categoria in categorias" :key="categoria" :value="categoria">
                        {{ categoria }}
                    </option>
                </select>
                <textarea class="resize-none w-full my-2 rounded-lg border-gray-200 bg-slate-100 p-3 text-sm" placeholder="Descripcion" rows="8" v-model="content"></textarea>   
                <div class="flex flex-col">
                    <button :disabled="!isFormValid" @click="actualizarPost()" class="py-2 px-4 bg-blue-600 text-white rounded-lg hover:bg-blue-500">Guardar Cambios</button>
                </div>  
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        props: ['ID'],
        data() {
            return {
                postID: this.ID,
                tittle: '',
                category: '',
                content: '',
                categorias: ['Noticia', 'Humor', 'Deportes', 'Juegos','Ayuda','Otros']
            }
        },
        computed: {
        isFormValid() {
            return this.tittle.trim() !== '' && this.category.trim() !== '' && this.content.trim() !== '';
            }
        },
        methods: {
            async actualizarPost() {
                if (!this.isFormValid) {
                    return;
                }
                try {
                    let result = await axios.patch(
                        `http://localhost:3000/posts/${this.postID}`,
                        {
                            tittle: this.tittle,
                            category: this.category,
                            content: this.content,
                        }
                    );
                    if (result.status==200) {
                        this.cerrar();
                        window.location.reload();
                    }
                } catch (error) {
                    console.error(error);
                }
            },
            cerrar() {
                this.$emit('close');
            }
        }
    }
</script>