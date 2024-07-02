<template>
    <div class="bg-slate-100 flex justify-center items-center h-screen">
        <div class="bg-white shadow w-80 p-6 rounded justify-center mb-10">
            <h1 class="font-inter text-center text-xl mb-4">Ingresa tu correo</h1>
            <div class="relative mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="absolute left-3 top-1/2 transform -translate-y-1/2 w-6 h-6">
                    <path d="M1.5 8.67v8.58a3 3 0 0 0 3 3h15a3 3 0 0 0 3-3V8.67l-8.928 5.493a3 3 0 0 1-3.144 0L1.5 8.67Z" />
                    <path d="M22.5 6.908V6.75a3 3 0 0 0-3-3h-15a3 3 0 0 0-3 3v.158l9.714 5.978a1.5 1.5 0 0 0 1.572 0L22.5 6.908Z" />
                </svg>
                <input type="email" placeholder="Correo Electronico" v-model="email" class="font-inter border-none pl-12 w-full text-base p-2 bg-slate-50 rounded">
            </div>
            <button @click="buscarUsuario" class="font-inter w-full border-none text-base bg-blue-600 rounded text-white p-2 mb-4 hover:bg-blue-500">Continuar</button>
            <router-link to="/login">
                <button class="font-inter w-full border-none text-base bg-gray-800 rounded text-white p-2 hover:bg-gray-700">Volver</button>
            </router-link>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                email: ''
            }
        },
        methods: {
            async buscarUsuario() {
                if (!this.email) {
                    alert('Por favor, completa todos los campos.');
                    return;
                }
                try {
                    let result = await axios.get(
                        `http://localhost:8081/api/users/email/${this.email}`
                    );
                    if (result.status == 200) {
                        let user = result.data;
                            
                        if (user) {
                            localStorage.setItem("usuarioContraseñaNueva", JSON.stringify(user));
                            this.$router.push({ name: 'changePassword' });
                        } else {
                            alert("No se encontró usuario");
                        }
                    }
                } catch (error) {
                    console.error(error)
                    alert('Ha ocurrido un error. Por favor, intenta nuevamente.')
                }
            }
        }
    }
</script>