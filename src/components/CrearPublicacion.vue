<template>

    <div class="holadiv">
        <div class="bg-white bg-opacity-50 flex justify-center items-center">


            <div class=" px-4 pt-5 pb-6 mx-auto mt-4 mb-6 bg-white rounded-xl shadow-2xl sm:w-full md:w-full lg:w-10/12 xl:w-10/12 "
                v-on:click="mostrarComponente(true)">
                <div class="flex flex-row ">

                    <div class=" w-1/12 mr-5">
                        <p>Imagen Usuario</p>
                    </div>

                    <div class="w-full flex flex-col justify-start px-4">
                        <input class=" w-4/6 sm:w-12/12  md:w-10/12 sm:text-sm md:text-md lg:text-md xl:text-lg "
                            type="text" placeholder="Escribe algo...">

                        <div class=" flex justify-between w-4/6 sm:w-12/12 md:w-10/12">
                            <button justify-be
                                class="rounded-lg text-sm font-medium px-4 py-2 text-gray-500 hover:bg-gray-100 hover:text-gray-800 hover:font-bold mt-2 sm:text-sm md:text-md lg:text-lg xl:text-xl  ">Fotos</button>
                            <button
                                class="rounded-lg text-sm font-medium px-4 py-2 text-gray-500 hover:bg-gray-100 hover:text-gray-800 hover:font-bold mt-2 sm:text-sm md:text-md lg:text-lg xl:text-xl ">Publicar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <div class="modal" v-show="mostrarCrearPublicacion">
            <div class="fixed bg-black opacity-80 inset-0"></div>

            <div class="fixed top-1/4 left-1/2 transform -translate-x-1/2 px-6 py-5 bg-white  rounded-xl shadow-lg   ">
                <div class="flex-auto justify-center   ">
                    <input class="w-full my-2 rounded-lg border-gray-200 p-3 text-sm" placeholder="Titulo" type="text"
                        v-model="titulo" />


                    <input type="file" @change="archivoSeleccionado">

                    <select v-model="categoriaSeleccionada">
                        <option disabled value="">Por favor selecciona una categoría</option>
                        <option v-for="categoria in categorias" :key="categoria" :value="categoria">
                            {{ categoria }}
                        </option>
                    </select>

                    <textarea class="w-full my-2 rounded-lg border-gray-200 p-3 text-sm" placeholder="Descripcion"
                        rows="8" v-model="descripcion"></textarea>
                </div>

                <div class="text-center space-x-2 my-3  sm:block ">
                    <button type=" submit"
                        class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto"
                        v-on:click="crearPublicacion">
                        Enviar
                    </button>

                    <button type="button" v-on:click="mostrarComponente(false)"
                        class="inline-block w-full rounded-lg bg-red-700 px-5 py-3 font-medium text-white sm:w-auto">
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
            categorias: ['Noticia', 'Entretenimiento', 'Deportes', 'Cine'],
            categoriaSeleccionada: '',
            descripcion: '',
            imagen: '',
            meGusta: 0,
            noMegusta: 0,
            comentarios:['']


        }
    },
    inject: ['refrescarPublicaciones'],
    methods: {
        mostrarComponente(valor) {
            this.mostrarCrearPublicacion = valor;
        },

        agregarCategoria() {

        },

        agregarImagen() {

        },
        archivoSeleccionado(evento) {
            console.log(evento);

            let files = evento.target.files || evento.dataTransfer.files;
            if (!files.length)
                return;
            this.crearImagenJSON(files[0]);

        },

        crearImagenJSON(archivo) {
            let leerArchivo = new FileReader();
            let vm = this;//referencia al componente

            leerArchivo.onload = (e) => {
                vm.imagen = e.target.result;
            };
            leerArchivo.readAsDataURL(archivo);
        },
        async crearPublicacion() {

            if (this.titulo != "" && this.descripcion != "") {
                const nuevaPublicacion = await crearPublicacionPost(datosUsuarioLogeado().id, this.titulo, this.categoria, this.imagen, this.descripcion, this.meGusta, this.noMegusta);
                console.log(nuevaPublicacion);
                this.mostrarComponente(false);
                this.refrescarPublicaciones();
            }
        },
                        
    },

}
</script>

<style></style>