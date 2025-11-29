package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.UsuarioDAOImplementation;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.ErrorCarga;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.RollJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioValidator;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioValidatorJPA;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("usuario")
public class UsuarioRestController {
    
    @Autowired
    UsuarioDAOImplementation usuarioDAOImplementation;
    
    @Autowired
    private UsuarioValidator usuarioValidator;
    
    @GetMapping
    public ResponseEntity GetAll() {
    
        Result result = new Result();
        
        try {
            
            result = usuarioDAOImplementation.GetAll();
            result.correct = true;
            result.errorMessage = "Se obtuvieron usuarios";
            result.status = 200;
        
        
        
        } catch (Exception ex) {//definir error en el servidor
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        
        }
    
        return ResponseEntity.status(result.status).body(result);
    }
    
    @PostMapping 
    public ResponseEntity Add(@RequestBody  UsuarioJPA usuario) {
        
         Result result = new Result();
         
         try {
             
            result = usuarioDAOImplementation.Add(usuario);
            result.correct = true;
            result.errorMessage = "Se agrego un nuevo usuario correctamente";
            result.status = 201;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }
         
    
         return ResponseEntity.status(result.status).body(result);
      
    
    }
    
    @PutMapping("/update")
     public ResponseEntity Update(@RequestBody  UsuarioJPA usuario) {
         
         Result result = new Result();
         
        try {
             
            result = usuarioDAOImplementation.Update(usuario);
            result.correct = true;
            result.errorMessage = "Se actualizo el usuario correctamente";
            result.status = 202;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }

         return ResponseEntity.status(result.status).body(result);
     }
     
     @DeleteMapping("/{idUsuario}")
     public ResponseEntity Delete(@PathVariable("idUsuario") int idUsuario) {
         
         Result result = new Result();
         
         try {
             
            result = usuarioDAOImplementation.Delete(idUsuario);
            result.correct = true;
            result.errorMessage = "Se elimino el usuario correctamente";
            result.status = 204;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }
         
         return ResponseEntity.status(result.status).body(result);
     
     }
     
     @GetMapping("/{idUsuario}")
     public ResponseEntity GetById(@PathVariable("idUsuario") int idUsuario) {
     
         Result result = new Result();
         
         try {
             
            result = usuarioDAOImplementation.GetById(idUsuario);
            
            if(result.object != null){
                
                result.errorMessage = "Se encontro un usuario con ese Id";
                result.status = 200;
                
            } else {
            
                result.correct = false;
                result.status = 404;
                result.errorMessage = "No se encontro un usuario";
            
            }
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }
         
         return ResponseEntity.status(result.status).body(result);
     
     }
     
     @PatchMapping("/{idUsuario}")
     public ResponseEntity UpdateImagen(@RequestBody HashMap<String, Object> imagen,
             @PathVariable("idUsuario") int idUsuario) {
     
         Result result = new Result();
         
         try {
            String imagenUpdate = (String) imagen.get("imagen"); 
            result = usuarioDAOImplementation.UpdateImagen(imagenUpdate, idUsuario);
            result.correct = true;
            result.errorMessage = "Se actualizo correctamente la imagen";
            result.status = 200;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 404;
         
         }
         
         return ResponseEntity.status(result.status).body(result);
     
     }
     
    @PostMapping("/dinamico")
    public ResponseEntity GetAllDinamico(@RequestBody  UsuarioJPA usuario) {
    
        Result result = new Result();
        
        try {
            
            result = usuarioDAOImplementation.GetAllDinamico(usuario);
            result.correct = true;
            
            
            result.errorMessage = "Se obtuvieron usuarios";
            result.status = 200;
        
        
        
        } catch (Exception ex) {//definir error en el servidor
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        
        }
    
        return ResponseEntity.status(result.status).body(result);
    }
    
    @CrossOrigin(origins = "http://localhost:8081")
    @PatchMapping("/updateStatus")
    public ResponseEntity UpdateStatus(@RequestBody UsuarioJPA usuario){
    
        Result result = new Result();
        
        try {
            
            result = usuarioDAOImplementation.UpdateStatus(usuario);
            result.correct = true;
            
            
            result.errorMessage = "Se ctualizo el status";
            result.status = 200;
        
        
        
        } catch (Exception ex) {//definir error en el servidor
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 401;
        
        }
    
        return ResponseEntity.status(result.status).body(result);
        
    }
    
    @PostMapping("/cargaMasiva")
    public ResponseEntity CargaMasiva(@RequestBody byte[] fileBytes){
        
        Result result = new Result();
        if(fileBytes != null) {
           
            //saber si txt o xlsx
            String tipo = getTypeFile(fileBytes);
            
            //ruta donde se guardara 
            String rutaCarpeta = "C:/Users/digis/OneDrive/Escritorio/Erika Mariana Carvajal Soriano/ECarvajalProgramacionEnCapasService/src/main/resources/ArchivosCarga/"; 
            
            //darle un identificador al archivo unico 
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            String id = ahora.format(formato);
            String rutaArchivo = rutaCarpeta + "mi_archivo" + id + "." + tipo;
            
            if(tipo != "Desconocido") {
                
                File archivo = new File( rutaArchivo);
                
                try {
                    
                    //guardar el archivo en el servidor
                    archivo.createNewFile();
                    result = llenarArchivo(rutaArchivo, fileBytes);
                    
                    List<UsuarioValidatorJPA> usuarios = new ArrayList<>();
                    String errores = "";
                    
                    if(tipo == "txt") {
                    
                         usuarios = LecturaTxt(archivo);
                         
                    } else {
                    
                        usuarios = LecturaXlsx(archivo);
                    
                    }
                    
                    if(usuarios != null) {
                        
                        errores = ValidarCampos(usuarios);
                    }//completar condicion
                    
                    if(errores.length() < 9) {//si no tiene errores el archivo 
                        
                        ManipularLog(archivo, 1, errores);
                       
                        result.object = "Su archivo no tiene errores";
                         
                    } else {
                        
                        ManipularLog(archivo, 2, errores);
                        result.object = errores;
                    
                    }
                    //regresar un dato que le indique al usuario si su archivo es valido o no
                    result.correct = true;
                    result.status = 200;
                
                } catch (IOException  ex) {
                
                    result.correct = false;
                    result.errorMessage = ex.getLocalizedMessage();
                    result.ex = ex;
                    
                }
                
            
            } else{
            
                result.correct = false;
                result.status = 400;
                return ResponseEntity.status(result.status).body(result);
            }
            
            
        }

        return ResponseEntity.status(result.status).body(result);

    }
    
    public Result llenarArchivo(String rutaArchivo, byte[] fileBytes) {
        
        Result result = new Result();
    
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
                fos.write(fileBytes);
                System.out.println("Se ha escrito el array de bytes en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.print("EL archivo llego");
        result.correct = true;
        result.status = 200;
        
        return result;
            
            
    }
    
    public String getTypeFile(byte[] fileBytes) {
        
        String tipo = "";
    
        if(fileBytes == null || fileBytes.length < 10) {
            
            tipo =  "Desconoccido";
        
        }
        
        else if (fileBytes[0] == (byte) 0x50 && fileBytes[1] == (byte) 0x4B) {
            tipo =  "xlsx";
        }
        
        boolean isPotentiallyText = true;
        
        for (int i = 0; i < Math.min(fileBytes.length, 10); i++) {
            if ((fileBytes[i] > 0x09 && fileBytes[i] < 0x0D) ||
                (fileBytes[i] > 0x0D && fileBytes[i] < 0x20) || 
                fileBytes[i] > 0x7E) {
                
                isPotentiallyText = false;
                break;
            }
        }
        
        if (isPotentiallyText) {
            tipo = "txt";
        }
        
        return tipo;
    }
   
    public Result ManipularLog(File archivoCarga, int status, String errores) {
        //status = 1 Sin errores
        //status = 2 Con errores
        
        Result resultLog = new Result();
        
        try {
        
            String rutaArchivoLog = "C:/Users/digis/OneDrive/Escritorio/Erika Mariana Carvajal Soriano/ECarvajalProgramacionEnCapasService/src/main/resources/LOG/LOG.txt";
            File archivoLog = new File(rutaArchivoLog);
            
            FileWriter writer = new FileWriter(archivoLog, true); // 'true' para añadir al final, 'false' para sobrescribir
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            
            //comenzar a escribir en el archivo 
            List<String> valoresLog = new ArrayList<>();
            //extraer fecha y hora
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy;HH:mm:ss");
            String fechaHora = ahora.format(formato);
            
            //antes de escribir en el log se debe validar el archivo
            switch(status) {
                
                //1 = acaba de llegar el archivo
                case 1 :
                    valoresLog.add(archivoCarga.getName());
                    valoresLog.add(generarToken(archivoCarga.getName()));
                    valoresLog.add(fechaHora);
                    valoresLog.add(Integer.toString(status));
                    valoresLog.add(errores);
                    valoresLog.add("Se creo sin errores");//Descripcion
                    break;
                    
                case 2 :
                    valoresLog.add(archivoCarga.getName());
                    valoresLog.add("");//token vacio porque tiene errores
                    valoresLog.add(fechaHora);
                    valoresLog.add(Integer.toString(status));
                    valoresLog.add(errores);
                    valoresLog.add("El archivo tiene errores");//Descripcion
                    break;
                
            }
            
            //Escribir en el log los valores
            
            bufferWriter.write(valoresLog.get(0) + "|" 
                    + valoresLog.get(1) + "|" 
                    + valoresLog.get(2) + "|"
                    + valoresLog.get(3) + "|"
                    + valoresLog.get(4) + "|"
                    + valoresLog.get(5) + "|"
                    + valoresLog.get(6) + "|");
            
            
            
            resultLog.correct = true;
            
                    
            
            
        } catch (IOException ex) {
        
            resultLog.correct = false;
            resultLog.errorMessage = ex.getLocalizedMessage();
            resultLog.ex = ex;
        
        }
        
        return resultLog;
    
    }
    
    public List<UsuarioValidatorJPA> LecturaTxt(File archivo) {
        List<UsuarioValidatorJPA> usuarios = new ArrayList<>();

        try (InputStream fileInputStream = new FileInputStream(archivo); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));) {
            String linea = "";

            while ((linea = bufferedReader.readLine()) != null) {

                String[] campos = linea.split("\\|");
                UsuarioValidatorJPA usuario = new UsuarioValidatorJPA();
                usuario.setUserName(campos[0]);
                usuario.setNombre(campos[1]);
                usuario.setApellidoPaterno(campos[2]);
                usuario.setApellidoMaterno(campos[3]);
                usuario.setEmail(campos[4]);
                usuario.setPassword(campos[5]);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                usuario.setFechaNacimiento(simpleDateFormat.parse(campos[6]));

                char sexo = campos[7].charAt(0);
                usuario.setSexo(sexo);

                usuario.setTelefono(campos[8]);
                usuario.setCelular(campos[9]);
                usuario.setCurp(campos[10]);

                usuario.Roll = new RollJPA();

                int number = Integer.parseInt(campos[11]);
                usuario.Roll.setIdRoll(number);

                System.out.println("usuario : " + usuario.getNombre());
                usuarios.add(usuario);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }

        return usuarios;

    }
    
    public List<UsuarioValidatorJPA> LecturaXlsx(File archivo) {
        List<UsuarioValidatorJPA> usuarios = new ArrayList<>();
        try (InputStream fileInputStream = new FileInputStream(archivo); XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            XSSFSheet workSheet = workbook.getSheetAt(0);

            for (Row row : workSheet) {

                UsuarioValidatorJPA usuario = new UsuarioValidatorJPA();
                usuario.setUserName(row.getCell(0).toString());
                usuario.setNombre(row.getCell(1).toString());
                usuario.setApellidoPaterno(row.getCell(2).toString());
                usuario.setApellidoMaterno(row.getCell(3).toString());
                usuario.setEmail(row.getCell(4).toString());
                usuario.setPassword(row.getCell(5).toString());

                String fecha = row.getCell(6).toString();
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                usuario.setFechaNacimiento(format.parse(fecha));

                char sexo = row.getCell(7).toString().charAt(0);
                usuario.setSexo(sexo);

                usuario.setTelefono(row.getCell(8).toString());
                usuario.setCelular(row.getCell(9).toString());
                usuario.setCurp(row.getCell(10).toString());
                usuario.Roll = new RollJPA();

                String number = row.getCell(11).toString();
                int value = number.charAt(0);
                usuario.Roll.setIdRoll(value);

                usuarios.add(usuario);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
        return usuarios;
    }
    
    public String ValidarCampos(List<UsuarioValidatorJPA> usuarios) {
        
        String Errores = "Errores:";
        
        List<ErrorCarga> erroresCarga = new ArrayList<>();

        int lineaError = 0;

        for (UsuarioValidatorJPA usuario : usuarios) {

            lineaError++;
            BindingResult bindingResult = usuarioValidator.validateObject(usuario);

            List<ObjectError> errors = bindingResult.getAllErrors();
            
            
            for (ObjectError error : errors) {

                FieldError fieldError = (FieldError) error;
                ErrorCarga errorCarga = new ErrorCarga();
                errorCarga.campo = fieldError.getField();
                errorCarga.descripcion = fieldError.getDefaultMessage();
                errorCarga.linea = lineaError;
                erroresCarga.add(errorCarga);
                
                Errores = Errores + "Campo: " 
                        + errorCarga.campo + ", "
                        + "Descripcion: " 
                        + errorCarga.descripcion 
                        + " Line: " + errorCarga.linea;
            }

        }
        return Errores;
    }
    
    public String generarToken(String clave) {
        
        String claveSecreta = clave;//la clave puede ser el nombre del archivo
        String token = "";
        
//        try {
//        
//            MessageDigest digest = new MessageDigest();
//        
//        } catch (NoSuchAlgorithmException ex) {
//        
//        
//        }
                
        return token;
    
    }
    
    
}
