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
    public class CarroController : Controller
    {
        // GET: api/<CarroController>
        [HttpGet]
        [Route("api/[controller]/listacarro")]
        public List<Carro> ListarCarros()
        {
            return CarroRepositorio.ListarCarros();
        }
    }
}