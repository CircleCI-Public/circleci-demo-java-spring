import React, { useState, useEffect } from 'react';
import { Line, Bar, Pie } from 'react-chartjs-2';
import ReactPlayer from 'react-player'

import Button from 'react-bootstrap/Button'
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'

import robotVideo from '../../mockData/Demo3.mp4';
import logJson from '../../mockData/log.json';

const AdminChart = (props) => {
    const [chartType, setChartType] = useState("video");
    const [rows, setRows ] = useState([])

    let logObj = {}
    let logData = []

    useEffect(() => {
        try {
            const loadData = () => JSON.parse(JSON.stringify(logJson));
            logData = loadData()
            logData.forEach((log, index) => {
                let logKeysArray = Object.values(log)
                logObj[index] = logKeysArray
            })
            Object.entries(logObj).forEach(([key,value]) => {
                let eventData = value[0] && value[0].split(',')
                rows.push(
                    <tr>
                        <td>{eventData[0]}</td>
                        <td>{eventData[1]}</td>
                        <td>{eventData[2]}</td>
                        <td>{eventData[3]}</td>
                        <td>{eventData[4]}</td>
                        <td>{eventData[5]}</td>
                        <td>{eventData[6]}</td>
                        <td>{eventData[7]}</td>
                    </tr>
                )
            })
            setRows(rows)
        } catch (error) {
            console.log(error)
        }
    }, [])

    let data = {}
    let options = {}
    let chartMaxWidth = "1200px"
    let chartHandler = [];

    switch (chartType) {
        case "video":
            chartHandler.push(
                <ReactPlayer 
                    url={[{src: robotVideo, type: 'video/mp4'}]} // video location
                    controls  // gives the front end video controls 
                    width='100%' 
                    height='100%'
                    config={{ file: { 
                        attributes: {
                            controlsList: 'nodownload'  //<- this is the important bit
                        }
                    }}}
                    onEnded={()=>console.log("ended")}
                />
            )
            break;
        case "charge":
            data = {
                labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                datasets: [
                    {
                        label: '# of Stations',
                        data: [12, 19, 3, 5, 2, 3],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                        ],
                        borderWidth: 1,
                    },
                ],
            };
            options = {
                scales: {
                    yAxes: [
                        {
                            ticks: {
                                beginAtZero: true,
                            },
                        },
                    ],
                },
            };
            chartHandler.push(<Bar data={data} options={options} />)
            break;
        case "raw":
            let table = 
                <table>
                    <thead>
                        <tr>
                            <th>Time</th>
                            <th>(x/y)</th>
                            <th>Direction</th>
                            <th>Surface</th>
                            <th>Clean Status</th>
                            <th>Dirt</th>
                            <th>Obstacles</th>
                            <th>Battery</th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                    </tbody>
                </table>;
            chartHandler.push(table);
            break;
        case "path":
            chartMaxWidth = "600px"
            data = {
                labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                datasets: [
                    {
                        label: 'Types of Obstacles',
                        data: [12, 19, 3, 5, 2, 3],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                        ],
                        borderWidth: 1,
                    },
                ],
            }
            chartHandler.push(<Pie data={data} />)
            break;
    }

    return (
    <>
        <div className='header'>
            <h3 className='title'>{props.title}</h3>
            <Row className='links my-4'>
                <Col className='d-flex justify-content-around'>
                    <Button className="w-50 btn-light mx-2" onClick={() => setChartType("video")}>
                        Visualize Robot
                    </Button>
                    <Button className="w-50 btn-light" onClick={() => setChartType("charge")}>
                        Recharging
                    </Button>
                    <Button className="w-50 btn-light mx-2" onClick={() => setChartType("path")}>
                        Path
                    </Button>
                    <Button className="w-50 btn-light" onClick={() => setChartType("raw")}>
                        Activity Log
                    </Button>
                </Col>
            </Row>
        </div>
        <Row>
            <Col xs={0} />
            <Col xs={12} style={{maxWidth:chartMaxWidth}}>
                {chartHandler}
            </Col>
            <Col xs={0} />
        </Row>
    </>
    )

}

export default AdminChart;