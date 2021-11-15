import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import LogOutButton from "../LogOutButton";
import AdminChart from "../AdminChart";

function Dashboard() {
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
                <Row style={{minHeight:"175px", display:"flex", flexDirection:"column", justifyContent:"center", alignItems:"center"}}>
                    <Col xl={10} xxl={8}>
                        <h1>CleanSweep Portal</h1>
                        <h3>Welcome User</h3>
                    </Col>
                </Row>
                <Row style={{minHeight:"300px", display:"flex", flexDirection:"column", justifyContent:"center", alignItems:"center"}}>
                    <Col xl={10} xxl={8}>
                        <AdminChart title={"Admin Metrics"}/>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default Dashboard;