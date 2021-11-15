import { useHistory } from "react-router-dom";

import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import LogOutButton from "../LogOutButton";

function RegisterDevice() {
    let history = useHistory();

    function handleClick() {
        history.push("/contact-us");
    }

    function handleRegister() {
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
                <Row style={{minHeight:"80vh", display:"flex", flexDirection:"column", justifyContent:"center", alignItems:"center"}}>
                    <Col xs={0} md={2}/>
                    <Col xs={12} md={8}>
                        <Form style={{textAlign:"left", background:"#5865F2", borderRadius:"20px", padding:80}}>
                            <h2 className="pb-4" style={{textAlign:"center", color:"#F6F6F6"}}>
                                Register Your Device
                            </h2>
                            <Form.Group className="my-4" controlId="formBasicEmail">
                                <Form.Label>Device ID</Form.Label>
                                <Form.Control type="email" placeholder="Enter your device id" />
                            </Form.Group>
                            <div className="d-grid gap-2">
                                <Button variant="outline-dark" className="mt-4" onClick={handleRegister}>
                                    Register
                                </Button>
                                <Button variant="link" style={{color:"#F6F6F6"}} onClick={handleClick}>
                                    Issue with your device? Contact us here!
                                </Button>
                            </div>
                        </Form>
                    </Col>
                    <Col xs={0} md={2}/>
                </Row>
            </Container>
        </div>
    )
}


export default RegisterDevice;