using Microsoft.AspNetCore.Mvc;
using ServicesWeb.Modelos;
using ServicesWeb.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Controllers
{
    [ApiController]
    public class AdministradorController : Controller
    {
        [HttpPost]
        [Route("api/[controller]/autentificaradministrador")]
        public string AutentificarAdministrador([FromBody] Administrador oAdministrador)
        {
            return AdministradorRepositorio.AutentificarAdministrador(oAdministrador);
        }

        // POST api/<AdministradorController>/{cDNIAdm}
        [HttpPost]
        [Route("api/[controller]/{cDNIAdm}")]
        public Administrador TraerDatosConductor(string cDNIAdm)
        {
            return AdministradorRepositorio.TraerDatosAdministrador(cDNIAdm);
        }

        // POST api/<AdministradorController>/actualizar
        [HttpPost]
        [Route("api/[controller]/actualizar")]
        public bool Actualizar([FromBody] Administrador oAdministrador)
        {
            return AdministradorRepositorio.Actualizar(oAdministrador);
        }

    }
}
