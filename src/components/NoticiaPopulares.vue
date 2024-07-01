<template>

    <div class="flex justify-center flex-none w-1/4">



        <div class="fixed flex flex-col bg-white rounded-xl xl:w-80 px-4 py-5 ">
            <div class=" flex justify-center">
                <div class="">
                    <span class=" text-base font-medium text-black  "> NOTICIAS POPULARES </span>
                </div>
            </div>

            <div class=" pt-6  space-y-1 lg:divide-y lg:divide-gray-500 " v-for="(publicacion, index) in publicaciones"
                :key="index">


                <div class=" flex flex-row justify-center">
                    <div class="p-3">
                        <img class="w-full rounded-md h-32 lg:w-32 object-cover" :src="publicacion.image" alt="text">
                    </div>
                    <div>
                        <span class="text-xs text-gray-500">JUNIO 24-06-2024</span>
                        <p class="mt-1 text-sm font-medium ">
                            <a class="text-gray-800 group-hover:text-gray-500 text-base">{{ publicacion.tittle }} </a>
                        </p>
                        <p class="mt-1 text-sm text-gray-500">{{ publicacion.content }}</p>
                    </div>
                </div>
            </div>



        </div>



    </div>

</template>

<script>
import { obtenerPublicacionesPorCategoria } from '@/services/foroService';



export default {
    data() {
        return {
            identificador: 'category',
            valor: 'noticias',
            publicaciones: []
        }
    },

    async mounted() {
        this.fetchPublicacionesNoticia();
    },
    methods: {

        async fetchPublicacionesNoticia() {
            this.publicaciones = await obtenerPublicacionesPorCategoria(this.valor);
        },




    }
}
</script>

<style></style>