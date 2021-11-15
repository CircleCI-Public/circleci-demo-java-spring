import { useHistory } from "react-router-dom";

import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import LogOutButton from "../LogOutButton";

function ContactUs() {
    let history = useHistory();

    function handleClick() {
        history.push("/register-device");
    }

    function handleSubmit() {
        history.push("/dashboard");
    }

    return (
        <div>
            <Container 
                fluid
                style={{
                    minHeight:"100vh",
                    background:"#282c34",
                    color: "#F6F6F6",
                    fontSize:"calc(10px + 1.5vmin)",
                }}>
                <Row className="pt-2">
                    <Col xs={2} md={2}/>
                    <Col xs={8} md={8}>
                    </Col>
                    <Col xs={2} md={2}>
                        <LogOutButton />
                    </Col>
                </Row>
                <Row style={{minHeight:"90vh", display:"flex", flexDirection:"column", justifyContent:"center", alignItems:"center"}}>
                    <Col xs={0} md={3}/>
                    <Col xs={12} md={6}>
                        <Form style={{textAlign:"left", background:"#5865F2", borderRadius:"20px", padding:80}}>
                            <h2 className="pb-4" style={{textAlign:"center", color:"#F6F6F6"}}>
                                Contact Us
                            </h2>
                            <Form.Group className="my-4" controlId="formBasicEmail">
                                <Form.Label>Email address</Form.Label>
                                <Form.Control type="email" placeholder="Enter email" />
                            </Form.Group>
                            <Form.Group className="my-4" controlId="message">
                                <Form.Label>Brief Message</Form.Label>
                                <Form.Control as="textarea" rows={3} placeholder="Tell us what's going on"/>
                            </Form.Group>
                            <div className="d-grid gap-2">
                                <Button variant="outline-dark" className="mt-4" onClick={handleSubmit}>
                                    Submit
                                </Button>
                                <Button variant="link" style={{color:"#F6F6F6"}} onClick={handleClick}>
                                    Return to Device Registration
                                </Button>
                            </div>
                        </Form>
                    </Col>
                    <Col xs={0} md={3}/>
                </Row>
            </Container>
        </div>
    )
}



export default ContactUs;