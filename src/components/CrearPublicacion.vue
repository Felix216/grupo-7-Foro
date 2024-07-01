<template>
    <div class="contenedor">
        <div class="bg-slate-200 bg-opacity-50 flex justify-center items-center">
            <div class="px-4 pt-5 pb-6 mb-6 bg-white rounded-xl shadow-lg sm:w-full md:w-full lg:w-10/12 xl:w-10/12" v-on:click="mostrarComponente(true)">
                <div class="flex flex-row">
                    <div class="w-1/12 mr-5">
                        <p>Imagen Usuario</p>
                    </div>
                    <div class="w-full flex flex-col justify-start px-4">
                        <input class="w-4/6 sm:w-12/12 md:w-10/12 sm:text-sm md:text-md lg:text-md xl:text-lg" type="text" placeholder="Escribe algo...">
                        <div class="flex justify-between w-4/6 sm:w-12/12 md:w-10/12">
                            <button class="rounded-lg text-sm font-medium px-5 py-2 text-white bg-emerald-500 hover:bg-emerald-600 transition hover:scale-110 duration-300 hover:font-semibold mt-2 sm:text-sm md:text-md lg:text-lg xl:text-xl">Fotos</button>
                            <button class="rounded-lg text-sm font-medium px-4 py-2 text-white bg-blue-600 hover:bg-blue-700 hover:font-semibold transition hover:scale-110 duration-300 mt-2 sm:text-sm md:text-md lg:text-lg xl:text-xl">Publicar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal" v-show="mostrarCrearPublicacion">
            <div class="fixed bg-black opacity-80 inset-0"></div>
            <div class="fixed top-1/4 left-1/2 transform -translate-x-1/2 px-6 py-5 bg-white rounded-xl shadow-lg">
                <div class="flex-auto justify-center">
                    <input class="w-full my-2 rounded-lg border-gray-200 p-3 text-sm" placeholder="Titulo" type="text" v-model="titulo" />
                    <input type="file" @change="handleFileUpload">
                    <select v-model="categoriaSeleccionada" class="flex flex-row mt-4 mb-2">
                        <option disabled value="">Por favor selecciona una categoría</option>
                        <option v-for="categoria in categorias" :key="categoria" :value="categoria">
                            {{ categoria }}
                        </option>
                    </select>
                    <textarea class="w-full my-2 rounded-lg border-gray-200 p-3 text-sm" placeholder="Descripcion" rows="8" v-model="descripcion"></textarea>
                </div>
                <div class="text-center space-x-2 my-3 sm:block">
                    <button type="submit" class="inline-block w-full rounded-lg bg-black px-5 py-3 mx-2 transition hover:scale-125 duration-300 font-medium text-white sm:w-auto" v-on:click="crearPublicacion">
                        Enviar
                    </button>
                    <button type="button" v-on:click="mostrarComponente(false)" class="inline-block w-full rounded-lg bg-red-700 px-5 py-3 mx-2 transition hover:scale-125 duration-300 font-medium text-white sm:w-auto">
                        Cancelar
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { crearPublicacionPost, datosUsuarioLogeado } from '@/services/foroService';

export default {
    data() {
        return {
            mostrarCrearPublicacion: false,
            isOpen: false,
            titulo: '',
            categorias: ['noticias', 'deportes','humor','juegos','ayuda','otros'],
            categoriaSeleccionada: '',
            descripcion: '',
            imagen:null,
            comentarios:['']


        }
    },
    inject: ['refrescarPublicaciones'],
    methods: {
        mostrarComponente(valor) {
            this.mostrarCrearPublicacion = valor;
        },
        
        handleFileUpload(event) {
            this.imagen = event.target.files[0];
        },
        async crearPublicacion() {

            if (this.titulo != "" && this.descripcion != "") {

                const nuevaPublicacion = await crearPublicacionPost(datosUsuarioLogeado().id, this.titulo, this.categoriaSeleccionada, this.descripcion, this.imagen);
                console.log("USUARIO NUMERO "+datosUsuarioLogeado().id +" | CREO UNA PUBLICACION ->  ",nuevaPublicacion);
                this.mostrarComponente(false);
                this.refrescarPublicaciones();
            }
        },
                        
    },

}
</script>

<style></style>