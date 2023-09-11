package com.sunbaseass.Controller;



import com.sunbaseass.Service.CustomerService;
import com.sunbaseass.model.Customer;
import com.sunbaseass.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/portal/api")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private CustomerService customerService;
    @Autowired
    private Customer customer;


    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(Model m) {
        return findPaginated(0, m);
    }
    @GetMapping("/page/{pageno}")
    public String findPaginated(@PathVariable int pageno, Model m) {

        Page<Customer> cuslist = customerService.getCustomerByPaginate(pageno, 2);
        m.addAttribute("customer", cuslist);
        m.addAttribute("currentPage", pageno);
        m.addAttribute("totalPages", cuslist.getTotalPages());
        m.addAttribute("totalItem", cuslist.getTotalElements());
        return "index";
    }
    @GetMapping("/addcustomer")
    public String addCustomerForm() {
        return "add_Customer";
    }

    @PostMapping("/register")
    public String customerRegister(@ModelAttribute Customer c, HttpSession session) throws Exception {
        customerService.createCustomer(c);
        session.setAttribute("msg", "Customer Added Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model m) {
        Customer c = customerService.getCustomerById(id);
        m.addAttribute("customer", c);
        return "edit";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer c, HttpSession session) throws Exception {
        customerService.createCustomer(c);
        session.setAttribute("msg", "Emp Data Update Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable long id, HttpSession session) {

//        service.deleteEMp(id);
        customerService.deleteCustomer(id);
        session.setAttribute("msg", "Emp Data Delete Sucessfully..");
        return "redirect:/";
    }
    //done

//    @PostMapping("/customer")
//    public String createCustomer(@ModelAttribute Customer c) throws Exception {
//        //Customer createdCustomer = customerService.createCustomer(customer);
//        System.out.println(c);
//
//        return "";
//    }
//
//    @GetMapping("/{username}")
//    public ResponseEntity<Customer> getUserByUsername(@PathVariable String username) {
//        Customer user = customerRepository.findByUsername(username);
//        if (user != null) {
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }
//    @GetMapping("/allCustomers")
//    public List<Customer> getAllCustomers(){
//        return this.customerService.getAllCustomers();
//    }
//    @GetMapping("hello")
//    public void println(){
//        System.out.println("ankita");
//    }
}




