using Microsoft.AspNetCore.Mvc;
using ServicesWeb.Modelos;
using ServicesWeb.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace ServicesWeb.Controllers
{

    [ApiController]
    public class ConductorController : ControllerBase
    {
        // GET: api/<ConductorController>
        [HttpGet]
        [Route("api/[controller]/listaconductor")]
        public List<Conductor> ListarConductores()
        {
            return ConductorRepositorio.ListarConductores();
        }

        [HttpPost]
        [Route("api/[controller]/autentificarconductor")]
        public Token AutentificarConductor([FromBody] Conductor oConductor)
        {
            return ConductorRepositorio.AutentificarConductor(oConductor);
        }

        // POST api/<ConductorController>/{cDNICond}
        [HttpPost]
        [Route("api/[controller]/{cDNICond}")]
        public Conductor TraerDatosConductor(string cDNICond)
        {
            return ConductorRepositorio.TraerDatosConductor(cDNICond);
        }

        // POST api/<ConductorController>
        [HttpPost]
        [Route("api/[controller]")]
        public bool Grabar([FromBody] Conductor oConductor)
        {
            return ConductorRepositorio.Grabar(oConductor);
        }

        // POST api/<ConductorController>
        [HttpPost]
        [Route("api/[controller]/actualizar")]
        public bool Actualizar([FromBody] Conductor oConductor)
        {
            return ConductorRepositorio.Actualizar(oConductor);
        }

    }
}