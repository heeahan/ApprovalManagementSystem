import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import './ApprLnTable.css'

function GetApprLn({ apprId }) {
    const [lines, setLines] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`/api/appr/getApprLn?apprId=${apprId}`)

                // const data = await response.data();
                setLines(response.data);
                console.log(response.data);
                console.log(lines);
            }
            catch (error) {
                setError(error);
                console.error(error);
            }
            finally {
                setLoading(false);
            }
        };
        fetchData();
    }, [apprId]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>품의서 로딩 실패했습니다.</div>;
    }

    // const letterToDivMap = {
    //     'A': '기안자',
    //     'B': '검토자',
    //     'C': '협조자',
    //     'D': '승인자',
    //     'E': '참조자',
    // };

    // const columnNames = [...new Set(lines.map(line => letterToDivMap[line[1]]))];

    return (
        <div className="appr-ln-container">
            <h2>결재선</h2>
            <ul>
                {lines.map((line, index) => (
                    <li key={index}> {line} </li>
                ))}
            </ul>

            {/* <table className="appr-ln-table">
                <thead>
                    <tr>
                        <th></th>
                        {columnNames.map((name, index) => (
                            <th key={index}>{name}</th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {lines.map((line, index) => (
                        <tr key={index}>
                            <td>{line[0]}</td>
                            {columnNames.map((columnName, colIndex) => (
                                <td key={colIndex}>
                                    {line[1] === Object.keys(letterToDivMap)[colIndex] ? "✔️" : ""}
                                </td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table> */}
        </div>

    )

    // return (
    //     <div className="appr-ln-container">
    //         <h2>품의서 결재선</h2>
    //         <table className="appr-ln-table">
    //             <thead>
    //                 <tr>
    //                     <th>기안</th>
    //                     <th>검토</th>
    //                     <th>협조</th>
    //                     <th>승인</th>
    //                 </tr>
    //             </thead>
    //             <tbody>
    //                 <tr>
    //                     <td>기안자id</td>
    //                     <td>검토자id</td>
    //                     <td>협조자들</td>
    //                     <td>승인자id</td>
    //                 </tr>
    //             </tbody>
    //         </table>
    //     </div>
    // )
};

export default GetApprLn;