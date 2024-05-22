<template>


  <div
    class="flex flex-col min-h-screen sm:w-2/12 md:w-3/12 lg:w-2/12 xl:w-2/12 justify-between items-center bg-slate-200" >
    
    <div class="fixed flex flex-col bg-white rounded-xl py-1 px-6   my-4 items-center md:px-4 lg:px-0 xl:px-6">


      
      <div class="flex flex-row">
        <router-link to="/Foro"
        class="relative flex items-center w-full rounded-lg text-base font-medium px-16 py-2 text-gray-500 text-center hover:bg-gray-300 hover:text-gray-900 hover:font-bold my-1
              md:px-8  lg:px-10 xl:px-14 xl:text-xl md:text-sm">
        <img alt="foro" src="@/assets/Home.png" class="absolute inset-0 object-contain  bg-black w-1/4 h-full rounded-2xl" />
              
              Principal</router-link>

      </div>
      
      <router-link to="/Popular"
        class="relative flex items-center rounded-lg text-base font-medium px-16 py-2 text-gray-500 text-center hover:bg-gray-300 hover:text-gray-900 hover:font-bold my-1
              md:px-8 lg:px-10 xl:px-14 xl:text-xl md:text-sm">
        <img alt="foro" src="@/assets/Popular.png" class="absolute inset-0 object-contain  w-1/4 h-full  rounded-2xl" />
        
        Popular</router-link>
      
 
      </div>

  </div>






</template>

<script>
export default {

}
</script>

<style></style>