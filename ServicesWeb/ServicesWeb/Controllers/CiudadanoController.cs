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
    public class CiudadanoController : Controller
    {
        [HttpPost]
        [Route("api/[controller]/autentificarciudadano")]
        public Token AutentificarCiudadano([FromBody] Ciudadano oCiudadano)
        {
            return CiudadanoRepositorio.AutentificarCiudadano(oCiudadano);
        }

        // POST api/<CiudadanoController>/{cDNICiud}
        [HttpPost]
        [Route("api/[controller]/{cDNICiud}")]
        public Ciudadano TraerDatosConductor(string cDNICiud)
        {
            return CiudadanoRepositorio.TraerDatosCiudadano(cDNICiud);
        }

        // POST api/<CiudadanoController>
        [HttpPost]
        [Route("api/[controller]/actualizar")]
        public bool Actualizar([FromBody] Ciudadano oCiudadano)
        {
            return CiudadanoRepositorio.Actualizar(oCiudadano);
        }

    }
}
