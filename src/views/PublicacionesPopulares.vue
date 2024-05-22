<template>
   
    <header class="bg-slate-200  " >
        <navbar-component class="bg-white"> </navbar-component>
    </header>

    <div class=" flex flex-row bg-slate-200 py-4 " >
        <aside-component class = "flex-none w-1/3" ></aside-component>


        <div class="flex flex-col  py-5  bg-slate-200  w-screen">
            <articulo-componente class="flex-grow" :publicacionesOrdenadas="publicacionesOrdenadas" :mostrar = "mostrar=true" > </articulo-componente>
            

        </div>

        <noticia-populares > </noticia-populares>

    </div>


    
    



    <footer-component>

    </footer-component>




</template>


<script>
import { obtenerPublicaciones } from '@/services/foroService';
import AsideComponent from '@/components/AsideComponente.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import NavbarComponent from '@/components/NavbarComponent.vue';
import NoticiaPopulares from '@/components/NoticiaPopulares.vue'
import ArticuloComponente from '@/components/ArticuloComponente.vue';

export default {
    components: {
        NavbarComponent,
        AsideComponent,
        FooterComponent,
        NoticiaPopulares,
        ArticuloComponente
    },

    data() {
        return {
            publicaciones: [],
            mostrar: false,

        }
    },

    async mounted() {
        this.fetchPublicaciones();
    },


    computed: {
        publicacionesOrdenadas() {
            return this.publicaciones.slice().sort((a, b) => b.likes - a.likes);
        },
    },
    methods: {
        async fetchPublicaciones() {
            this.publicaciones = await obtenerPublicaciones();
        },



    }
   

}
</script>

<style></style>