<template>
    <div class="bg-slate-100 h-screen flex justify-center items-center">
        <div class="bg-white shadow w-80 p-6 rounded">
            <h1 class="font-inter text-center text-xl mb-4">Ingresa tus datos para registrarte.</h1>
            <div class="relative mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="absolute left-3 top-1/2 transform -translate-y-1/2 w-6 h-6">
                    <path fill-rule="evenodd" d="M7.5 6a4.5 4.5 0 1 1 9 0 4.5 4.5 0 0 1-9 0ZM3.751 20.105a8.25 8.25 0 0 1 16.498 0 .75.75 0 0 1-.437.695A18.683 18.683 0 0 1 12 22.5c-2.786 0-5.433-.608-7.812-1.7a.75.75 0 0 1-.437-.695Z" clip-rule="evenodd" />
                </svg>
                <input type="text" placeholder="Nombre de Usuario" v-model="username" class="font-inter border-none  pl-12 w-full text-base p-2 bg-slate-50 rounded">
            </div>
            <div class="relative mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="absolute left-3 top-1/2 transform -translate-y-1/2 w-6 h-6">
                    <path fill-rule="evenodd" d="M7.5 6a4.5 4.5 0 1 1 9 0 4.5 4.5 0 0 1-9 0ZM3.751 20.105a8.25 8.25 0 0 1 16.498 0 .75.75 0 0 1-.437.695A18.683 18.683 0 0 1 12 22.5c-2.786 0-5.433-.608-7.812-1.7a.75.75 0 0 1-.437-.695Z" clip-rule="evenodd" />
                </svg>
                <input type="text" placeholder="Nombre" v-model="name" class="font-inter border-none  pl-12 w-full text-base p-2 bg-slate-50 rounded">
            </div>
            <div class="relative mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="absolute left-3 top-1/2 transform -translate-y-1/2 w-6 h-6">
                    <path fill-rule="evenodd" d="M7.5 6a4.5 4.5 0 1 1 9 0 4.5 4.5 0 0 1-9 0ZM3.751 20.105a8.25 8.25 0 0 1 16.498 0 .75.75 0 0 1-.437.695A18.683 18.683 0 0 1 12 22.5c-2.786 0-5.433-.608-7.812-1.7a.75.75 0 0 1-.437-.695Z" clip-rule="evenodd" />
                </svg>
                <input type="text" placeholder="Apellido" v-model="lastname" class="font-inter border-none  pl-12 w-full text-base p-2 bg-slate-50 rounded">
            </div>
            <div class="relative mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="absolute left-3 top-1/2 transform -translate-y-1/2 w-6 h-6">
                    <path d="M1.5 8.67v8.58a3 3 0 0 0 3 3h15a3 3 0 0 0 3-3V8.67l-8.928 5.493a3 3 0 0 1-3.144 0L1.5 8.67Z" />
                    <path d="M22.5 6.908V6.75a3 3 0 0 0-3-3h-15a3 3 0 0 0-3 3v.158l9.714 5.978a1.5 1.5 0 0 0 1.572 0L22.5 6.908Z" />
                </svg>
                <input type="email" placeholder="Correo Electronico" v-model="email" class="font-inter border-none  pl-12 w-full text-base p-2 bg-slate-50 rounded">
            </div>
            <div class="relative mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="absolute left-3 top-1/2 transform -translate-y-1/2 w-6 h-6">
                    <path fill-rule="evenodd" d="M15.75 1.5a6.75 6.75 0 0 0-6.651 7.906c.067.39-.032.717-.221.906l-6.5 6.499a3 3 0 0 0-.878 2.121v2.818c0 .414.336.75.75.75H6a.75.75 0 0 0 .75-.75v-1.5h1.5A.75.75 0 0 0 9 19.5V18h1.5a.75.75 0 0 0 .53-.22l2.658-2.658c.19-.189.517-.288.906-.22A6.75 6.75 0 1 0 15.75 1.5Zm0 3a.75.75 0 0 0 0 1.5A2.25 2.25 0 0 1 18 8.25a.75.75 0 0 0 1.5 0 3.75 3.75 0 0 0-3.75-3.75Z" clip-rule="evenodd" />
                </svg>
                <input type="password" placeholder="Contraseña" v-model="password" class="font-inter border-none  pl-12 w-full text-base p-2 bg-slate-50 rounded">
            </div>
            <button @click="registrarse" class="font-inter w-full border-none text-base bg-blue-600 rounded text-white p-2 mb-4 hover:bg-blue-500">Crear Cuenta</button>
            <router-link to="/login">
                <button class="font-inter w-full border-none text-base bg-gray-900 rounded text-white p-2 hover:bg-gray-700">¿Ya tienes cuenta?</button>
            </router-link>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                username: '',
                name: '',
                lastname: '',
                email: '',
                password: ''
            }
        },
        methods: {
            async registrarse() {
                if (!this.validarCampos()) {
                    return;
                }
                try {
                    let result = await axios.post("http://localhost:3000/users", {
                        username: this.username,
                        name: this.name,
                        lastname: this.lastname,
                        email: this.email,
                        description: '',
                        profilePicture: "https://i.pinimg.com/550x/57/70/f0/5770f01a32c3c53e90ecda61483ccb08.jpg",
                        profileBanner: "https://htmlcolorcodes.com/assets/images/colors/gray-color-solid-background-1920x1080.png",
                        password: this.password
                    });

                    if (result.status == 201) {
                        this.$router.push({ name: 'login' });
                    }
                } catch (error) {
                    console.error(error);
                    alert('Ocurrió un error durante el registro. Por favor, intenta nuevamente.');
                }
            },
            validarCampos() {
                if (!this.username || !this.name || !this.lastname || !this.email || !this.password) {
                    alert('Por favor, completa todos los campos.');
                    return false;
                }

                if (this.username.length > 20 || this.name.length > 20 || this.lastname.length > 20) {
                    alert('El nombre de usuario, nombre o apellido no deben superar los 20 caracteres.');
                    return false;
                }

                if (!this.validarEmail(this.email)) {
                    alert('Por favor, ingresa un correo electrónico válido.');
                    return false;
                }

                if (!this.contraseñaEsSegura(this.password)) {
                    alert('La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial.');
                    return false;
                }

                return true;
            },
            validarEmail(email) {
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                return emailRegex.test(email);
            },
            contraseñaEsSegura(password) {
                let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
                return passwordRegex.test(password);
            }
        }
    }
</script>