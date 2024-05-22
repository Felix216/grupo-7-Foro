<template>
    <div class="bg-slate-100 flex justify-center items-center h-screen">
        <div class="bg-white shadow w-80 p-6 rounded justify-center mb-10">
            <h1 class="font-inter text-center text-xl mb-4">Ingresa tu nueva contraseña</h1>
            <div class="relative mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="absolute left-3 top-1/2 transform -translate-y-1/2 w-6 h-6">
                    <path fill-rule="evenodd" d="M15.75 1.5a6.75 6.75 0 0 0-6.651 7.906c.067.39-.032.717-.221.906l-6.5 6.499a3 3 0 0 0-.878 2.121v2.818c0 .414.336.75.75.75H6a.75.75 0 0 0 .75-.75v-1.5h1.5A.75.75 0 0 0 9 19.5V18h1.5a.75.75 0 0 0 .53-.22l2.658-2.658c.19-.189.517-.288.906-.22A6.75 6.75 0 1 0 15.75 1.5Zm0 3a.75.75 0 0 0 0 1.5A2.25 2.25 0 0 1 18 8.25a.75.75 0 0 0 1.5 0 3.75 3.75 0 0 0-3.75-3.75Z" clip-rule="evenodd" />
                </svg>
                <input type="password" placeholder="Contraseña" v-model="password" class="font-inter border-none  pl-12 w-full text-base p-2 bg-slate-50 rounded">
            </div>
            <div class="relative mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="absolute left-3 top-1/2 transform -translate-y-1/2 w-6 h-6">
                    <path fill-rule="evenodd" d="M15.75 1.5a6.75 6.75 0 0 0-6.651 7.906c.067.39-.032.717-.221.906l-6.5 6.499a3 3 0 0 0-.878 2.121v2.818c0 .414.336.75.75.75H6a.75.75 0 0 0 .75-.75v-1.5h1.5A.75.75 0 0 0 9 19.5V18h1.5a.75.75 0 0 0 .53-.22l2.658-2.658c.19-.189.517-.288.906-.22A6.75 6.75 0 1 0 15.75 1.5Zm0 3a.75.75 0 0 0 0 1.5A2.25 2.25 0 0 1 18 8.25a.75.75 0 0 0 1.5 0 3.75 3.75 0 0 0-3.75-3.75Z" clip-rule="evenodd" />
                </svg>
                <input type="password" placeholder="Repite Contraseña" v-model="repeatPassword" class="font-inter border-none  pl-12 w-full text-base p-2 bg-slate-50 rounded">
            </div>
            <button @click="cambiarContraseña" class="font-inter w-full border-none text-base bg-blue-600 rounded text-white p-2 hover:bg-blue-500">Confirmar</button>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        data() {
            return {
                password: '',
                repeatPassword: '',
                user: []
            }
        },
        mounted() {
            let userJSON = localStorage.getItem("usuarioContraseñaNueva");
            this.user = JSON.parse(userJSON);
        },
        methods: {
            async cambiarContraseña() {
                if (!this.validarContraseña()) {
                    return;
                }
                try {
                    let result = await axios.patch(
                        `http://localhost:3000/users/${this.user.id}`,
                        {
                            password: this.password
                        }
                    );
                    if (result.status === 200) {
                        localStorage.removeItem("usuarioContraseñaNueva");
                        this.$router.push({name:'login'});
                    } else {
                        alert("Contraseña invalida")
                    }
                } catch (error) {
                    console.error(error)
                    alert('Ha ocurrido un error. Por favor, intenta nuevamente.')
                }
            },
            validarContraseña() {
                if (!this.contraseñaEsSegura(this.password)) {
                    alert('La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial.');
                    return false;
                }
                if (this.password != this.repeatPassword) {
                    alert('Las contraseñas deben ser iguales.');
                    return false;
                }
                return true;
            },
            contraseñaEsSegura(password) {
                let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
                return passwordRegex.test(password);
            }
        }
    }
</script>