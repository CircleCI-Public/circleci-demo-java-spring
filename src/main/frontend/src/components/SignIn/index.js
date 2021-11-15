import { useHistory } from "react-router-dom";

import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

function SignIn() {
    let history = useHistory();

    function handleClick() {
        history.push("/create-account");
    }

    function handleSubmit() {
        history.push("/register-device");
    }

    return (
        <div>
            <Container 
                fluid
                style={{
                    background:"#282c34",
                    color: "#F6F6F6",
                    fontSize:"calc(10px + 1.5vmin)",
                }}>
                <Row style={{minHeight:"100vh", display:"flex", flexDirection:"column", justifyContent:"center", alignItems:"center"}}>
                    <Col xs={0} md={3}/>
                    <Col xs={12} md={6}>
                        <Form style={{textAlign:"left", background:"#5865F2", borderRadius:"20px", padding:80}}>
                            <h2 className="pb-4" style={{textAlign:"center", color:"#F6F6F6"}}>
                                Sign In
                            </h2>
                            <Form.Group className="my-4" controlId="formBasicEmail">
                                <Form.Label>Email address</Form.Label>
                                <Form.Control type="email" placeholder="Enter email" />
                            </Form.Group>
                            <Form.Group className="my-4" controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" placeholder="Enter Password" />
                            </Form.Group>
                            <div className="d-grid gap-2">
                                <Button variant="outline-dark" className="mt-4" onClick={handleSubmit}>
                                    Submit
                                </Button>
                                <Button variant="link" style={{color:"#F6F6F6"}} onClick={handleClick}>
                                    Don't have an account? Click to sign up here!
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


export default SignIn;