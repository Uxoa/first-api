package org.factoriaf5;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/saludo")
public class FirstController {
    
    @GetMapping()
    public String saludo() {
        return "hola";
    }

    @PostMapping()
    public String saludo2(@RequestBody String name) {
        return "Hola por POST: " + name;
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        return "Quieres eliminar el recurso?";
    }
    
    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody String name){
        return "vas a cambiar el nombre por: " + name;
    }
}
