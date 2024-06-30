<template>
   
    <header class="bg-slate-200  " >
        <navbar-component class="bg-white"> </navbar-component>
    </header>

    <div class=" flex flex-row bg-slate-200 py-4 " >
        <aside-component class = "flex-none w-1/3" ></aside-component>


        <div class="flex flex-col  py-5  bg-slate-200  w-screen">
            <!-- <articulo-componente class="flex-grow" :publicacionesOrdenadas="publicacionesOrdenadas" :mostrar = "mostrar=true" > </articulo-componente> -->
            <articulo-componente class="flex-grow" :publicacionesOrdenadas="publicacionesOrdenadas" :mostrar="mostrar=true"></articulo-componente>

        </div>

        <noticia-populares > </noticia-populares>

    </div>


    
    



    <footer-component>

    </footer-component>




</template>


<script>
import { obtenerPublicacionesPopulares, obtenerComentarios,obtenerInteraccion, obtenerUsuarioPorID } from '@/services/foroService';
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
            publicacionesCompleta:[],
            mostrar: false,

        }
    },

    async mounted() {
        this.fetchPublicaciones();
    },


    computed: {
        publicacionesOrdenadas() {
            //return this.publicaciones.slice().sort((a, b) => b.likes - a.likes);
            return this.publicacionesCompleta;
        },
    },
    methods:{
        async fetchPublicaciones(){

            const publicaciones = await obtenerPublicacionesPopulares();
    
            // Iterar sobre cada publicación para obtener y almacenar sus comentarios
            for (const publicacion of publicaciones) {
              const comentarios = await obtenerComentarios(publicacion.id);
              const interaccion = await obtenerInteraccion(publicacion.id);
              const usuarios = await obtenerUsuarioPorID(publicacion.user);
              // Agregar la publicación junto con sus comentarios al arreglo
              this.publicacionesCompleta.push({
                publicaciones: publicacion,
                comentarios: comentarios,
                interaccion:interaccion,
                usuarios: usuarios

              });
            }
        }
   
    }
}
</script>

<style></style>