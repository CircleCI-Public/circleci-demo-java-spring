import { useHistory } from "react-router-dom";

import Button from 'react-bootstrap/Button';

function LogOutButton() {
    let history = useHistory();

    function handleLogout() {
        history.push("/sign-in")
    }

    return (
        <Button className="mr-2 my-2" variant="outline-light" onClick={handleLogout}>LogOut</Button>
    )
}


export default LogOutButton;