<template>
    <div class="bg-slate-200 h-screen flex items-center justify-center">
        <div class="w-1/4 bg-white rounded-lg shadow flex flex-col mx-auto p-6 justify-center">
            <h1 class="text-center mb-2">No se encontro el usuario.</h1>
            <button @click="volver" class="bg-blue-600 rounded text-white p-1 hover:bg-blue-500">Volver</button>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                
            }
        },
        methods: {
            volver() {
                this.$router.go(-1);
            }
        }
    }
</script>