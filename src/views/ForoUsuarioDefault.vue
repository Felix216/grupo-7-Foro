<template>


  <header>
    <navbar-component>    </navbar-component>
  </header>

  <div class=" flex flex-row bg-slate-200 py-4 ">
    <aside-componente class="flex-none w-1/3 "></aside-componente>


    <div class="flex flex-col flex-1 py-5  bg-slate-200 ">
      <articulo-componente class="flex-grow" ref="ArticuloComponent"> </articulo-componente>
    </div>

    <noticia-populares ref="NoticiaPopulares"> </noticia-populares>

  </div>


  <footer-component> </footer-component>

</template>

<script>
import NavbarComponent from '@/components/NavbarComponent.vue';
import AsideComponente from '@/components/AsideComponente.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import ArticuloComponente from '@/components/ArticuloComponente.vue';
import NoticiaPopulares from '@/components/NoticiaPopulares.vue'

export default {
  components: {

    NavbarComponent,
    AsideComponente,
    FooterComponent,
    ArticuloComponente,
    NoticiaPopulares,
  },
  methods: {
    async refrescarPublicaciones() {
      //accedo al componente articuloComponent al metodo fetchPublicacion con el metodo $refs
      this.$refs.ArticuloComponent.fetchPublicaciones();
    }
  }

}
</script>

<style></style>