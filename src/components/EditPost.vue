<template>
    <div @click="cerrar()" class="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50">
        <div class="bg-white rounded shadow p-8 w-1/4" @click.stop>
            <button @click="cerrar()" class="text-gray-500 transition hover:text-gray-600 absolute ml-72">
                <svg class="h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                </svg>
            </button>
            <div>           
            </div>
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
            cerrar() {
                this.$emit('close');
            }
        }
    }
</script>