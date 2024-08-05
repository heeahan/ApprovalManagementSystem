import React, { useState, useEffect } from "react";
import './ApprLnTable.css'

function GetApprLn () {

    return (
        <div className="appr-ln-container">
            <h2>품의서 결재선</h2>
            <table className="appr-ln-table">
                <thead>
                    <tr>
                        <th>기안</th>
                        <th>검토</th>
                        <th>협조</th>
                        <th>승인</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>기안자id</td>
                        <td>검토자id</td>
                        <td>협조자들</td>
                        <td>승인자id</td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
};

export default GetApprLn;