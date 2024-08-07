import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import './ApprLnTable.css'

function GetApprLn({ apprId }) {
    const [lines, setLines] = useState([]);
    const [line, setLine] = useState([]);
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

    const letterToDivMap = {
        'A': '기안자',
        'B': '검토자',
        'C': '협조자',
        'D': '승인자',
        'E': '참조자',
    };

    const apprProcMap = {
        '1': '기안',
        '2': '승인',
        '3': '반려',
        '4': '협조',
        '5': '협조이견',
    }

    const formatDate = (isoDate) => {
        if (!isoDate) return '';
        const date = new Date(isoDate);
        return date.toLocaleDateString({
            year: 'numeric',
            month: 'numeric',
            day: 'numeric'
        }) + '  ' + date.toLocaleTimeString('en-US', {
            hour: '2-digit',
            minute: '2-digit'
        })
    };

    const transposeData = (lines) => {
        const numCols = lines[0].length;
        const transposed = Array.from({ length: numCols }, () => []);

        lines.forEach((line) => {
            line.forEach((item, index) => {
                transposed[index].push(item);
            });
        });

        return transposed;
    };

    // Transpose the lines data
    const transposedLines = transposeData(lines);

    return (
        <div className="appr-ln-container">
            <h2>품의서 결재선</h2>
            <table className="appr-ln-table">
                <tbody>
                    {transposedLines.map((column, index) => (
                        <tr key={index}>
                            {column.map((item, colIndex) => (
                                <td key={colIndex}>
                                    {index === 1 ? letterToDivMap[item] : index === 3 ? apprProcMap[item] || '-' : index === 4 ? formatDate(item) : item || '-'}
                                </td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );

    /*
    return (
        <div className="appr-ln-container">
            <h2>품의서 결재선</h2>
            <table className="appr-ln-table">
                <thead>
                    <tr>
                        <th>결재선</th>
                        <th>승인 구분</th>
                        <th>사용자 ID</th>
                        <th>승인 처리</th>
                        <th>승인 처리 일시</th>
                        <th>코멘트</th>
                    </tr>
                </thead>
                <tbody>
                    {lines.map((line, index) => (
                        <tr key={index}>
                            <td>{line[0]}</td>
                            <td>{letterToDivMap[line[1]]}</td>
                            <td>{line[2]}</td>
                            <td>{apprProcMap[line[3]] || '-'}</td>
                            <td>{formatDate(line[4]) || '-'}</td>
                            <td>{line[5] || ''}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
*/

};

export default GetApprLn;